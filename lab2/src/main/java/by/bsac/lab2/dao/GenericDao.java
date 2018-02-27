package by.bsac.lab2.dao;

import by.bsac.lab2.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    List<T> findAll() throws DaoException;

    Optional<T> find(long id) throws DaoException;

    void create(T cutFlower) throws DaoException;

    void update(T cutFlower) throws DaoException;

    void delete(long id) throws DaoException;
}
