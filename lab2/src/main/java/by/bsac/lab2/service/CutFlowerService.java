package by.bsac.lab2.service;

import by.bsac.lab2.dao.CutFlowerDao;
import by.bsac.lab2.entity.CutFlower;
import by.bsac.lab2.exception.ServiceException;

import java.util.List;

public interface CutFlowerService {

    void setCutFlowerDao(CutFlowerDao cutFlowerDao);

    List<CutFlower> findCutFlowers() throws ServiceException;

    CutFlower findById(long id) throws ServiceException;

    void createCutFlower(CutFlower cutFlower) throws ServiceException;

    void updateCutFlower(CutFlower cutFlower) throws ServiceException;

    void deleteCutFlower(long id) throws ServiceException;

    void addToBouquet(long flowerId, long bouquetId) throws ServiceException;
}
