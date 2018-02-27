package by.bsac.lab2.dao;

import by.bsac.lab2.entity.Accessory;
import by.bsac.lab2.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AccessoryDao extends GenericDao<Accessory> {
    @Override
    List<Accessory> findAll() throws DaoException;

    @Override
    Optional<Accessory> find(long id) throws DaoException;

    @Override
    void create(Accessory accessory) throws DaoException;

    @Override
    void update(Accessory accessory) throws DaoException;

    @Override
    void delete(long id) throws DaoException;

    void addToBouquet(long accessoryId, long bouquetId) throws DaoException;

    List<Accessory> findByBouquetId(long id) throws DaoException;
}
