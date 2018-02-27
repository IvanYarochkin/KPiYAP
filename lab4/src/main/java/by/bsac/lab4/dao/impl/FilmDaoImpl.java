package by.bsac.lab4.dao.impl;

import by.bsac.lab4.dao.FilmDao;
import by.bsac.lab4.entity.Film;
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

public class FilmDaoImpl implements FilmDao {
    private static final String FIND_ALL_SQL = "SELECT id, `name`, release_date, country FROM film;";
    private static final String FIND_SQL = "SELECT id, `name`, release_date, country FROM film WHERE id = ?;";
    private static final String DELETE_SQL = "DELETE FROM `film` WHERE `id`=?;";

    @Override
    public List<Film> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Film> films = new ArrayList<>();
            while (resultSet.next()) {
                films.add(createFilm(resultSet));
            }
            return films;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Film> find(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Film> film = Optional.empty();
            while (resultSet.next()) {
                film = Optional.of(createFilm(resultSet));
            }
            return film;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().acquireConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Film createFilm(ResultSet resultSet) throws SQLException {
        return new Film(resultSet.getLong("id"), resultSet.getString("name"),
                resultSet.getDate("release_date").toLocalDate(), resultSet.getString("country"));
    }
}
