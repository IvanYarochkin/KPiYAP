package by.bsac.lab4.service;

import by.bsac.lab4.entity.Film;
import by.bsac.lab4.exception.ServiceException;

import java.util.List;

public interface FilmService {

    List<Film> findFilmsCurrentAndLastYears() throws ServiceException;

    void deleteFilmPreviouslySetDate(int years) throws ServiceException;
}
