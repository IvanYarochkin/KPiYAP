package by.bsac.lab2.dao;

import by.bsac.lab2.entity.CutFlower;
import by.bsac.lab2.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CutFlowerDao extends GenericDao<CutFlower> {

    @Override
    List<CutFlower> findAll() throws DaoException;

    @Override
    Optional<CutFlower> find(long id) throws DaoException;

    @Override
    void create(CutFlower cutFlower) throws DaoException;

    @Override
    void update(CutFlower cutFlower) throws DaoException;

    @Override
    void delete(long id) throws DaoException;

    void addToBouquet(long flowerId, long bouquetId) throws DaoException;

    List<CutFlower> findByBouquetId(long id) throws DaoException;
}
