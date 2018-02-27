package by.bsac.lab2.service.impl;

import by.bsac.lab2.dao.GardenFlowerDao;
import by.bsac.lab2.dao.impl.GardenFlowerDaoImpl;
import by.bsac.lab2.entity.GardenFlower;
import by.bsac.lab2.exception.DaoException;
import by.bsac.lab2.exception.ServiceException;
import by.bsac.lab2.service.GardenFlowerService;

import java.util.List;
import java.util.Optional;

public class GardenFlowerServiceImpl implements GardenFlowerService {
    private GardenFlowerDao gardenFlowerDao = new GardenFlowerDaoImpl();

    @Override
    public void setGardenFlowerDao(GardenFlowerDao gardenFlowerDao) {
        this.gardenFlowerDao = gardenFlowerDao;
    }

    @Override
    public List<GardenFlower> findGardenFlowers() throws ServiceException {
        try {
            return gardenFlowerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public GardenFlower findById(long id) throws ServiceException {
        try {
            Optional<GardenFlower> flower = gardenFlowerDao.find(id);

            if ( flower.isPresent() ) {
                return flower.get();
            } else {
                throw new ServiceException("There are not a flower having id " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createGardenFlower(GardenFlower gardenFlower) throws ServiceException {
        try {
            gardenFlowerDao.create(gardenFlower);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateGardenFlower(GardenFlower gardenFlower) throws ServiceException {
        try {
            gardenFlowerDao.update(gardenFlower);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteGardenFlower(long id) throws ServiceException {
        try {
            gardenFlowerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
