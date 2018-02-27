package by.bsac.lab2.dao;

import by.bsac.lab2.entity.Bouquet;
import by.bsac.lab2.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BouquetDao extends GenericDao<Bouquet> {

    @Override
    List<Bouquet> findAll() throws DaoException;

    @Override
    Optional<Bouquet> find(long id) throws DaoException;

    @Override
    void create(Bouquet bouquet) throws DaoException;

    @Override
    void update(Bouquet bouquet) throws DaoException;

    @Override
    void delete(long id) throws DaoException;
}
