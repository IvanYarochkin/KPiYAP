package by.bsac.lab2.service.impl;

import by.bsac.lab2.dao.AccessoryDao;
import by.bsac.lab2.dao.BouquetDao;
import by.bsac.lab2.dao.CutFlowerDao;
import by.bsac.lab2.dao.impl.AccessoryDaoImpl;
import by.bsac.lab2.dao.impl.BouquetDaoImpl;
import by.bsac.lab2.dao.impl.CutFlowerDaoImpl;
import by.bsac.lab2.entity.Bouquet;
import by.bsac.lab2.exception.DaoException;
import by.bsac.lab2.exception.ServiceException;
import by.bsac.lab2.service.BouquetService;

import java.util.List;
import java.util.Optional;

public class BouquetServiceImpl implements BouquetService {
    private BouquetDao bouquetDao = new BouquetDaoImpl();
    private CutFlowerDao cutFlowerDao = new CutFlowerDaoImpl();
    private AccessoryDao accessoryDao = new AccessoryDaoImpl();

    @Override
    public void setBouquetDao(BouquetDao bouquetDao) {
        this.bouquetDao = bouquetDao;
    }

    @Override
    public void setCutFlowerDao(CutFlowerDao cutFlowerDao) {
        this.cutFlowerDao = cutFlowerDao;
    }

    @Override
    public void setAccessoryDao(AccessoryDao accessoryDao) {
        this.accessoryDao = accessoryDao;
    }

    @Override
    public List<Bouquet> findBouquets() throws ServiceException {
        try {
            List<Bouquet> bouquets = bouquetDao.findAll();
            for (Bouquet bouquet : bouquets) {
                findFlowersAndAccessoryByBouquetId(bouquet);
            }
            return bouquets;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Bouquet findById(long id) throws ServiceException {
        try {
            Optional<Bouquet> bouquet = bouquetDao.find(id);
            if ( bouquet.isPresent() ) {
                return findFlowersAndAccessoryByBouquetId(bouquet.get());
            } else {
                throw new ServiceException("There are not a bouquet having id " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createBouquet(Bouquet bouquet) throws ServiceException {
        try {
            bouquetDao.create(bouquet);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBouquet(Bouquet bouquet) throws ServiceException {
        try {
            bouquetDao.update(bouquet);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBouquet(long id) throws ServiceException {
        try {
            bouquetDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private Bouquet findFlowersAndAccessoryByBouquetId(Bouquet bouquet) throws DaoException {
        bouquet.setFlowers(cutFlowerDao.findByBouquetId(bouquet.getId()));
        bouquet.setAccessories(accessoryDao.findByBouquetId(bouquet.getId()));
        return bouquet;
    }
}
