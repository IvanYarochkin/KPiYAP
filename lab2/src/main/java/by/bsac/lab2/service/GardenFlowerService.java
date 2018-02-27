package by.bsac.lab2.service;

import by.bsac.lab2.dao.GardenFlowerDao;
import by.bsac.lab2.entity.GardenFlower;
import by.bsac.lab2.exception.ServiceException;

import java.util.List;

public interface GardenFlowerService {

    void setGardenFlowerDao(GardenFlowerDao gardenFlowerDao);

    List<GardenFlower> findGardenFlowers() throws ServiceException;

    GardenFlower findById(long id) throws ServiceException;

    void createGardenFlower(GardenFlower gardenFlower) throws ServiceException;

    void updateGardenFlower(GardenFlower gardenFlower) throws ServiceException;

    void deleteGardenFlower(long id) throws ServiceException;
}
