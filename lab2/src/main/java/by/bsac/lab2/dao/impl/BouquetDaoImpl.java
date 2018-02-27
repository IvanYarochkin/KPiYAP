package by.bsac.lab2.dao.impl;

import by.bsac.lab2.dao.BouquetDao;
import by.bsac.lab2.entity.Bouquet;
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
public class BouquetDaoImpl implements BouquetDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name` FROM bouquet;";
    private static final String FIND_BY_ID_SQL = "SELECT id, `name` FROM bouquet WHERE id = ?;";
    private static final String CREATE_SQL = "INSERT INTO `bouquet` (`name`) VALUES (?);";
    private static final String DELETE_SQL = "DELETE FROM bouquet WHERE id=?;";
    private static final String UPDATE_SQL = "UPDATE `bouquet` SET `name`=? WHERE `id`=?;";

    @Override
    public List<Bouquet> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Bouquet> bouquets = new ArrayList<>();
            while (resultSet.next()) {
                bouquets.add(createBouquet(resultSet));
            }
            return bouquets;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Bouquet> find(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Bouquet> bouquet = Optional.empty();
            while (resultSet.next()) {
                bouquet = Optional.of(createBouquet(resultSet));
            }
            return bouquet;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Bouquet bouquet) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, bouquet.getName());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Bouquet bouquet) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, bouquet.getName());
            statement.setLong(2, bouquet.getId());
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

    private Bouquet createBouquet(ResultSet resultSet) throws SQLException, FlowerException {
        return new Bouquet(resultSet.getLong("id"), resultSet.getString("name"));
    }
}
