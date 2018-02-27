package by.bsac.lab4.main;

import by.bsac.lab4.exception.ConnectionPoolException;
import by.bsac.lab4.exception.ServiceException;
import by.bsac.lab4.pool.ConnectionPool;
import by.bsac.lab4.service.FilmService;
import by.bsac.lab4.service.StaffService;
import by.bsac.lab4.service.impl.FilmServiceImpl;
import by.bsac.lab4.service.impl.StaffServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) throws ConnectionPoolException, ServiceException {
        ConnectionPool.getInstance().initializeConnectionPool(1, "jdbc:mysql://localhost:3306/videolibrary?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");

        StaffService staffService = new StaffServiceImpl();
        FilmService filmService = new FilmServiceImpl();

        System.out.println("All this and the last years films:");
        filmService.findFilmsCurrentAndLastYears().forEach(System.out::println);

        System.out.println("\nAll actors starting in a film having 1 id:");
        staffService.findActorsByFilm(1).forEach(System.out::println);

        System.out.println("\nAll actors starting in more than one film:");
        staffService.findActorsStartingInFilmsMoreThanRang(2).forEach(System.out::println);

        System.out.println("\nAll actors were producers:");
        staffService.findActorsWereProducers().forEach(System.out::println);
    }
}
