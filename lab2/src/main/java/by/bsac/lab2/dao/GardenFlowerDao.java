package by.bsac.lab2.dao;

import by.bsac.lab2.entity.GardenFlower;
import by.bsac.lab2.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface GardenFlowerDao extends GenericDao<GardenFlower> {

    @Override
    List<GardenFlower> findAll() throws DaoException;

    @Override
    Optional<GardenFlower> find(long id) throws DaoException;

    @Override
    void create(GardenFlower gardenFlower) throws DaoException;

    @Override
    void update(GardenFlower gardenFlower) throws DaoException;

    @Override
    void delete(long id) throws DaoException;
}
