package by.bsac.lab4.service;

import by.bsac.lab4.entity.Staff;
import by.bsac.lab4.exception.ServiceException;

import java.util.List;

public interface StaffService {

    List<Staff> findActorsByFilm(long filmId) throws ServiceException;

    List<Staff> findActorsStartingInFilmsMoreThanRang(int range) throws ServiceException;

    List<Staff> findActorsWereProducers() throws ServiceException;
}
