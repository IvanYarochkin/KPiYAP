package by.bsac.lab2.service;

import by.bsac.lab2.dao.AccessoryDao;
import by.bsac.lab2.entity.Accessory;
import by.bsac.lab2.exception.ServiceException;

import java.util.List;

public interface AccessoryService {

    void setAccessoryDao(AccessoryDao accessoryDao);

    List<Accessory> findAccessories() throws ServiceException;

    Accessory findById(long id) throws ServiceException;

    void createAccessory(Accessory accessory) throws ServiceException;

    void updateAccessory(Accessory accessory) throws ServiceException;

    void deleteAccessory(long id) throws ServiceException;

    void addToBouquet(long flowerId, long bouquetId) throws ServiceException;
}
