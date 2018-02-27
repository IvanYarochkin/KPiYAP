package by.bsac.lab4.dao;

import by.bsac.lab4.entity.Film;
import by.bsac.lab4.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface FilmDao {

    List<Film> findAll() throws DaoException;

    Optional<Film> find(long id) throws DaoException;

    void remove(long id) throws DaoException;
}
