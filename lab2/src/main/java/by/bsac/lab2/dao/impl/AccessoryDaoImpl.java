package by.bsac.lab2.dao.impl;

import by.bsac.lab2.dao.AccessoryDao;
import by.bsac.lab2.entity.Accessory;
import by.bsac.lab2.exception.ConnectionPoolException;
import by.bsac.lab2.exception.DaoException;
import by.bsac.lab2.exception.FlowerException;
import by.bsac.lab2.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public class AccessoryDaoImpl implements AccessoryDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name`, price FROM accessory;";
    private static final String FIND_BY_ID_SQL = "SELECT id, `name`, price FROM accessory WHERE id = ?;";
    private static final String CREATE_SQL = "INSERT INTO `accessory` (`name`, `price`) VALUES (?, ?);";
    private static final String DELETE_SQL = "DELETE FROM accessory WHERE id=?;";
    private static final String ADD_TO_BOUQUET = "UPDATE `accessory` SET `id_bouquet_fk`=? WHERE `id`=?;";
    private static final String UPDATE_SQL = "UPDATE `accessory` SET `name`=?, `price`=? WHERE `id`=?;";
    private static final String FIND_BY_BOUQUET_ID_SQL = "SELECT id, `name`, price FROM accessory WHERE id_bouquet_fk = ?;";

    @Override
    public List<Accessory> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Accessory> accessories = new ArrayList<>();
            while (resultSet.next()) {
                accessories.add(createAccessory(resultSet));
            }
            return accessories;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Accessory> find(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Accessory> accessory = Optional.empty();
            while (resultSet.next()) {
                accessory = Optional.of(createAccessory(resultSet));
            }
            return accessory;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Accessory> findByBouquetId(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_BOUQUET_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Accessory> accessories = new ArrayList<>();
            while (resultSet.next()) {
                accessories.add(createAccessory(resultSet));
            }
            return accessories;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Accessory accessory) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, accessory.getName());
            statement.setDouble(2, accessory.getPrice());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Accessory accessory) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, accessory.getName());
            statement.setDouble(2, accessory.getPrice());
            statement.setLong(3, accessory.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addToBouquet(long accessoryId, long bouquetId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TO_BOUQUET)) {
            statement.setLong(1, bouquetId);
            statement.setLong(2, accessoryId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Accessory createAccessory(ResultSet resultSet) throws SQLException, FlowerException {
        return new Accessory(resultSet.getLong("id"), resultSet.getString("name"),
                resultSet.getDouble("price"));
    }
}
