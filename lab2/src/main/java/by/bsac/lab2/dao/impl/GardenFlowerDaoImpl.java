package by.bsac.lab2.dao.impl;

import by.bsac.lab2.dao.GardenFlowerDao;
import by.bsac.lab2.entity.GardenFlower;
import by.bsac.lab2.entity.MultiplyingType;
import by.bsac.lab2.entity.SoilType;
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
public class GardenFlowerDaoImpl implements GardenFlowerDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name`, stem_length, color, multiplying, soil FROM garden_flower;";
    private static final String FIND_BY_ID_SQL = "SELECT id, `name`, stem_length, color, multiplying, soil FROM garden_flower WHERE id = ?;";
    private static final String CREATE_SQL = "INSERT INTO `garden_flower` (`name`, `stem_length`, `color`, " +
            "`multiplying`, `soil`) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM garden_flower WHERE id=?;";
    private static final String UPDATE_SQL = "UPDATE `garden_flower` SET `name`=?, `stem_length`=?, `color`=?," +
            "`multiplying`=?, `soil`=? WHERE `id`=?;";


    @Override
    public List<GardenFlower> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<GardenFlower> flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(createFlower(resultSet));
            }
            return flowers;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<GardenFlower> find(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<GardenFlower> flower = Optional.empty();
            while (resultSet.next()) {
                flower = Optional.of(createFlower(resultSet));
            }
            return flower;
        } catch (SQLException | ConnectionPoolException | FlowerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void create(GardenFlower gardenFlower) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, gardenFlower.getName());
            statement.setInt(2, gardenFlower.getStemLength());
            statement.setString(3, gardenFlower.getColor());
            statement.setString(4, gardenFlower.getMultiplying().toString().toLowerCase());
            statement.setString(5, gardenFlower.getSoil().toString().toLowerCase());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(GardenFlower gardenFlower) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, gardenFlower.getName());
            statement.setInt(2, gardenFlower.getStemLength());
            statement.setString(3, gardenFlower.getColor());
            statement.setString(4, gardenFlower.getMultiplying().toString().toLowerCase());
            statement.setString(5, gardenFlower.getSoil().toString().toLowerCase());
            statement.setLong(6, gardenFlower.getId());
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

    private GardenFlower createFlower(ResultSet resultSet) throws SQLException, FlowerException {
        return new GardenFlower(resultSet.getLong("id"), resultSet.getString("name"),
                resultSet.getInt("stem_length"), resultSet.getString("color"),
                MultiplyingType.valueOf(resultSet.getString("multiplying").toUpperCase()),
                SoilType.valueOf(resultSet.getString("soil").toUpperCase()));
    }
}
