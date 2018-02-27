package by.bsac.lab4.service.impl;

import by.bsac.lab4.dao.StaffDao;
import by.bsac.lab4.dao.impl.StaffDaoImpl;
import by.bsac.lab4.entity.Staff;
import by.bsac.lab4.exception.DaoException;
import by.bsac.lab4.exception.ServiceException;
import by.bsac.lab4.service.StaffService;

import java.util.List;
import java.util.stream.Collectors;

public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDaoImpl();

    @Override
    public List<Staff> findActorsByFilm(long filmId) throws ServiceException {
        try {
            return staffDao.findActorsByFilm(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> findActorsStartingInFilmsMoreThanRang(int range) throws ServiceException {
        try {
            List<Staff> actors = staffDao.findAll();
            return actors.stream()
                    .filter(actor -> actor.getFilmCount() >= range)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> findActorsWereProducers() throws ServiceException {
        try {
            return staffDao.findActorsWereProducers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
