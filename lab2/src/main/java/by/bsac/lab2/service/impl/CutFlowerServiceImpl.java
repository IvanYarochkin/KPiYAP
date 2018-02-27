package by.bsac.lab2.service.impl;

import by.bsac.lab2.dao.CutFlowerDao;
import by.bsac.lab2.dao.impl.CutFlowerDaoImpl;
import by.bsac.lab2.entity.CutFlower;
import by.bsac.lab2.exception.DaoException;
import by.bsac.lab2.exception.ServiceException;
import by.bsac.lab2.service.CutFlowerService;

import java.util.List;
import java.util.Optional;

public class CutFlowerServiceImpl implements CutFlowerService {
    private CutFlowerDao cutFlowerDao = new CutFlowerDaoImpl();

    @Override
    public void setCutFlowerDao(CutFlowerDao cutFlowerDao) {
        this.cutFlowerDao = cutFlowerDao;
    }

    @Override
    public List<CutFlower> findCutFlowers() throws ServiceException {
        try {
            return cutFlowerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CutFlower findById(long id) throws ServiceException {
        try {
            Optional<CutFlower> flower = cutFlowerDao.find(id);

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
    public void createCutFlower(CutFlower cutFlower) throws ServiceException {
        try {
            cutFlowerDao.create(cutFlower);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCutFlower(CutFlower cutFlower) throws ServiceException {
        try {
            cutFlowerDao.update(cutFlower);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCutFlower(long id) throws ServiceException {
        try {
            cutFlowerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addToBouquet(long flowerId, long bouquetId) throws ServiceException {
        try {
            cutFlowerDao.addToBouquet(flowerId, bouquetId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
