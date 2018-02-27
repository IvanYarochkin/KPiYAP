package by.bsac.lab2.dao.impl;

import by.bsac.lab2.dao.CutFlowerDao;
import by.bsac.lab2.entity.CutFlower;
import by.bsac.lab2.entity.MultiplyingType;
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
public class CutFlowerDaoImpl implements CutFlowerDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name`, stem_length, color, multiplying, price, freshness FROM cut_flower;";
    private static final String FIND_BY_ID_SQL = "SELECT id, `name`, stem_length, color, multiplying, price, freshness FROM cut_flower WHERE id = ?;";
    private static final String CREATE_SQL = "INSERT INTO `cut_flower` (`name`, `stem_length`, `color`, " +
            "`multiplying`, `price`, `freshness`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM cut_flower WHERE id=?;";
    private static final String ADD_TO_BOUQUET_SQL = "UPDATE `cut_flower` SET `id_bouquet_fk`=? WHERE `id`=?;";
    private static final String UPDATE_SQL = "UPDATE `cut_flower` SET `name`=?, `stem_length`=?, `color`=?," +
            "`multiplying`=?, `price`=?, `freshness`=? WHERE `id`=?;";
    private static final String FIND_BY_BOUQUET_ID_SQL = "SELECT id, `name`, stem_length, color, multiplying, price," +
            " freshness FROM cut_flower WHERE id_bouquet_fk=?;";


    @Override
    public List<CutFlower> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<CutFlower> flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(createFlower(resultSet));
            }
            return flowers;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<CutFlower> find(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<CutFlower> flower = Optional.empty();
            while (resultSet.next()) {
                flower = Optional.of(createFlower(resultSet));
            }
            return flower;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<CutFlower> findByBouquetId(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_BOUQUET_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<CutFlower> flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(createFlower(resultSet));
            }
            return flowers;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(CutFlower cutFlower) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, cutFlower.getName());
            statement.setInt(2, cutFlower.getStemLength());
            statement.setString(3, cutFlower.getColor());
            statement.setString(4, cutFlower.getMultiplying().toString().toLowerCase());
            statement.setDouble(5, cutFlower.getPrice());
            statement.setInt(6, cutFlower.getFreshness());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CutFlower cutFlower) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, cutFlower.getName());
            statement.setInt(2, cutFlower.getStemLength());
            statement.setString(3, cutFlower.getColor());
            statement.setString(4, cutFlower.getMultiplying().toString().toLowerCase());
            statement.setDouble(5, cutFlower.getPrice());
            statement.setInt(6, cutFlower.getFreshness());
            statement.setLong(7, cutFlower.getId());
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
    public void addToBouquet(long flowerId, long bouquetId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TO_BOUQUET_SQL)) {
            statement.setLong(1, bouquetId);
            statement.setLong(2, flowerId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private CutFlower createFlower(ResultSet resultSet) throws SQLException, FlowerException {
        return new CutFlower(resultSet.getLong("id"), resultSet.getString("name"),
                resultSet.getInt("stem_length"), resultSet.getString("color"),
                MultiplyingType.valueOf(resultSet.getString("multiplying").toUpperCase()),
                resultSet.getInt("freshness"), resultSet.getDouble("price"));
    }
}
