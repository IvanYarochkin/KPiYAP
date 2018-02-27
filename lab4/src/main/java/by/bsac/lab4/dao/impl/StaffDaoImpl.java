package by.bsac.lab4.dao.impl;

import by.bsac.lab4.dao.StaffDao;
import by.bsac.lab4.entity.Staff;
import by.bsac.lab4.exception.ConnectionPoolException;
import by.bsac.lab4.exception.DaoException;
import by.bsac.lab4.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffDaoImpl implements StaffDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name`, last_name, birth_date, role, COUNT(staff.id) AS" +
            "`film_count` FROM staff LEFT JOIN film_staff ON film_staff.id_staff_fk = staff.id GROUP BY staff.id;";
    private static final String FIND_ACTORS_BY_FILM_SQL = "SELECT staff.id, staff.`name`, last_name, birth_date, role FROM staff\n" +
            "LEFT JOIN film_staff ON film_staff.id_staff_fk = staff.id LEFT JOIN film ON film_staff.id_film_fk = film.id WHERE film.id = ?;";
    private static final String FIND_FILM_PRODUCER_SQL = "SELECT staff.id, staff.`name`, last_name, birth_date, role FROM staff\n" +
            "RIGHT JOIN film ON film.id_producer_fk = staff.id WHERE film.id = ?;";
    private static final String FIND_ACTOR_WAS_PRODUCER_SQL = "SELECT staff.id, staff.`name`, last_name, birth_date, role FROM `staff` " +
            "RIGHT JOIN film ON film.id_producer_fk = staff.id WHERE staff.role = \"actor\";";

    @Override
    public Optional<Staff> findFilmProducer(long filmId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FILM_PRODUCER_SQL)) {
            statement.setLong(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            Optional<Staff> producer = Optional.empty();
            while (resultSet.next()) {
                producer = Optional.of(createStaff(resultSet));
            }
            return producer;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Staff> findActorsByFilm(long filmId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACTORS_BY_FILM_SQL)) {
            statement.setLong(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            List<Staff> stuffs = new ArrayList<>();
            while (resultSet.next()) {
                stuffs.add(createStaff(resultSet));
            }
            return stuffs;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Staff> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Staff> stuffs = new ArrayList<>();
            while (resultSet.next()) {
                Staff stuff = new Staff(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getDate("birth_date").toLocalDate(),
                        resultSet.getString("role"), resultSet.getInt("film_count"));
                stuffs.add((stuff));
            }
            return stuffs;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Staff> findActorsWereProducers() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ACTOR_WAS_PRODUCER_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Staff> stuffs = new ArrayList<>();
            while (resultSet.next()) {
                stuffs.add(createStaff(resultSet));
            }
            return stuffs;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Staff createStaff(ResultSet resultSet) throws SQLException {
        return new Staff(resultSet.getLong("id"), resultSet.getString("name"),
                resultSet.getString("last_name"), resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("role"));
    }
}
