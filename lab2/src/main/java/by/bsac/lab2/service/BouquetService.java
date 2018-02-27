package by.bsac.lab2.service;

import by.bsac.lab2.dao.AccessoryDao;
import by.bsac.lab2.dao.BouquetDao;
import by.bsac.lab2.dao.CutFlowerDao;
import by.bsac.lab2.entity.Bouquet;
import by.bsac.lab2.exception.ServiceException;

import java.util.List;

public interface BouquetService {

    void setBouquetDao(BouquetDao bouquetDao);

    void setAccessoryDao(AccessoryDao accessoryDao);

    void setCutFlowerDao(CutFlowerDao cutFlowerDao);

    List<Bouquet> findBouquets() throws ServiceException;

    Bouquet findById(long id) throws ServiceException;

    void createBouquet(Bouquet bouquet) throws ServiceException;

    void updateBouquet(Bouquet bouquet) throws ServiceException;

    void deleteBouquet(long id) throws ServiceException;
}
