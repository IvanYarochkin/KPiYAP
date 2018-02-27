package by.bsac.lab4.pool;

import by.bsac.lab4.exception.ConnectionPoolException;
import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private static ReentrantLock instanceLock = new ReentrantLock();

    private static ConnectionPool instance;

    private ArrayDeque<Connection> freeConnections;

    private ArrayDeque<Connection> busyConnections;

    private Lock connectionLock;

    private Condition connectionCondition;

    private ConnectionPool() {
        connectionLock = new ReentrantLock();
        connectionCondition = connectionLock.newCondition();
        freeConnections = new ArrayDeque<>();
        busyConnections = new ArrayDeque<>();
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, PoolConstant.DRIVER_REGISTER_ERROR_MESSAGE, e);
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        if ( !instanceCreated.get() ) {
            instanceLock.lock();
            try {
                if ( instance == null ) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public void initializeConnectionPool(int connectionSize, String url) throws ConnectionPoolException {
        try {
            for (int i = 0; i < connectionSize; i++) {
                Connection connection = DriverManager.getConnection(url,
                        PropertyHelper.getInstance().getUsernameToDB(),
                        PropertyHelper.getInstance().getPasswordToDB());
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.push(proxyConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    int getFreeSize() {
        return freeConnections.size();
    }

    int getBusySize() {
        return busyConnections.size();
    }

    public Connection acquireConnection() throws ConnectionPoolException {
        connectionLock.lock();
        try {
            if ( !freeConnections.isEmpty() || !busyConnections.isEmpty() ) {
                if ( freeConnections.isEmpty() ) {
                    connectionCondition.await();
                }
                Connection connection = freeConnections.poll();
                busyConnections.push(connection);
                return connection;
            } else {
                LOGGER.log(Level.FATAL, PoolConstant.CONNECTION_SIZE_IS_NULL_MESSAGE);
                throw new RuntimeException(PoolConstant.CONNECTION_SIZE_IS_NULL_MESSAGE);
            }
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionLock.unlock();
        }
    }

    void putBackConnection(Connection connection) throws ConnectionPoolException {
        if ( connection != null ) {
            try {
                connectionLock.lock();
                if ( busyConnections.remove(connection) ) {
                    freeConnections.push(connection);
                    if ( freeConnections.size() == 1 ) {
                        connectionCondition.signal();
                    }
                } else {
                    throw new ConnectionPoolException(PoolConstant.CONNECTION_IS_NOT_IN_BUSY_MESSAGE);
                }
            } finally {
                connectionLock.unlock();
            }
        } else {
            throw new ConnectionPoolException(PoolConstant.NULL_VALUE_MESSAGE);
        }
    }

    public void closeConnections() throws ConnectionPoolException {
        try {
            connectionLock.lock();
            TimeUnit.SECONDS.sleep(1);

            for (Connection connection : freeConnections) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.getConnection().close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        } finally {
            connectionLock.unlock();
        }
    }
}
