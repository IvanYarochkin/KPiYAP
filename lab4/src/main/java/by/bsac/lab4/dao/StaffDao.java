package by.bsac.lab4.dao;

import by.bsac.lab4.entity.Staff;
import by.bsac.lab4.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface StaffDao {

    Optional<Staff> findFilmProducer(long filmId) throws DaoException;

    List<Staff> findActorsByFilm(long filmId) throws DaoException;

    List<Staff> findAll() throws DaoException;

    List<Staff> findActorsWereProducers() throws DaoException;
}
