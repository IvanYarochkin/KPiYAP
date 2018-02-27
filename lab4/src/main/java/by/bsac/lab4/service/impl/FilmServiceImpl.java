package by.bsac.lab4.service.impl;

import by.bsac.lab4.dao.FilmDao;
import by.bsac.lab4.dao.StaffDao;
import by.bsac.lab4.dao.impl.FilmDaoImpl;
import by.bsac.lab4.dao.impl.StaffDaoImpl;
import by.bsac.lab4.entity.Film;
import by.bsac.lab4.entity.Staff;
import by.bsac.lab4.exception.DaoException;
import by.bsac.lab4.exception.ServiceException;
import by.bsac.lab4.service.FilmService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilmServiceImpl implements FilmService {
    private FilmDao filmDao = new FilmDaoImpl();
    private StaffDao staffDao = new StaffDaoImpl();

    @Override
    public List<Film> findFilmsCurrentAndLastYears() throws ServiceException {
        try {
            List<Film> films = filmDao.findAll();
            films = films.stream()
                    .filter(film -> film.getReleaseDate().isAfter(LocalDate.of(LocalDate.now().getYear() - 1, 1, 1)))
                    .collect(Collectors.toList());
            for (Film film : films) {
                film.setActors(staffDao.findActorsByFilm(film.getId()));
                Optional<Staff> producer = staffDao.findFilmProducer(film.getId());
                if ( producer.isPresent() ) {
                    film.setProducer(producer.get());
                }
            }
            return films;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteFilmPreviouslySetDate(int years) throws ServiceException {
        try {
            List<Film> films = filmDao.findAll();
            films = films.stream()
                    .filter(film -> film.getReleaseDate().isAfter(LocalDate.of(LocalDate.now().getYear() - years, 1, 1)))
                    .collect(Collectors.toList());

            for (Film film : films) {
                filmDao.remove(film.getId());
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
