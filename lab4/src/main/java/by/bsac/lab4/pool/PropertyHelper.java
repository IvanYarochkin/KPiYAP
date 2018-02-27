package by.bsac.lab4.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class PropertyHelper {

    private static final Logger LOGGER = LogManager.getRootLogger();

    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private static ReentrantLock instanceLock = new ReentrantLock();

    private static PropertyHelper instance;

    private Properties properties;

    private PropertyHelper() {
        try {
            properties = new Properties();
            properties.load(PropertyHelper.class.getResourceAsStream(PoolConstant.PROPERTY_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.INFO, PoolConstant.OPEN_PROPERTY_ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public static PropertyHelper getInstance() {
        if ( !instanceCreated.get() ) {
            instanceLock.lock();
            try {
                if ( instance == null ) {
                    instance = new PropertyHelper();
                    instanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    String getUsernameToDB() {
        return properties.getProperty(PoolConstant.PROPERTY_USERNAME);
    }

    String getPasswordToDB() {
        return properties.getProperty(PoolConstant.PROPERTY_PASSWORD);
    }
}
