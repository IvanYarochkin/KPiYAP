package by.bsac.lab2.service.impl;

import by.bsac.lab2.dao.AccessoryDao;
import by.bsac.lab2.dao.impl.AccessoryDaoImpl;
import by.bsac.lab2.entity.Accessory;
import by.bsac.lab2.exception.DaoException;
import by.bsac.lab2.exception.ServiceException;
import by.bsac.lab2.service.AccessoryService;

import java.util.List;
import java.util.Optional;

public class AccessoryServiceImpl implements AccessoryService {
    private AccessoryDao accessoryDao = new AccessoryDaoImpl();

    @Override
    public void setAccessoryDao(AccessoryDao accessoryDao) {
        this.accessoryDao = accessoryDao;
    }

    @Override
    public List<Accessory> findAccessories() throws ServiceException {
        try {
            return accessoryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Accessory findById(long id) throws ServiceException {
        try {
            Optional<Accessory> flower = accessoryDao.find(id);

            if ( flower.isPresent() ) {
                return flower.get();
            } else {
                throw new ServiceException("There are not an accessory having id " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createAccessory(Accessory accessory) throws ServiceException {
        try {
            accessoryDao.create(accessory);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateAccessory(Accessory accessory) throws ServiceException {
        try {
            accessoryDao.update(accessory);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAccessory(long id) throws ServiceException {
        try {
            accessoryDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addToBouquet(long flowerId, long bouquetId) throws ServiceException {
        try {
            accessoryDao.addToBouquet(flowerId, bouquetId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
