package by.bsac.lab2.menu;

import by.bsac.lab2.action.FlowerFinder;
import by.bsac.lab2.action.FlowerSorter;
import by.bsac.lab2.entity.Bouquet;
import by.bsac.lab2.entity.CutFlower;
import by.bsac.lab2.exception.ServiceException;
import by.bsac.lab2.service.BouquetService;
import by.bsac.lab2.service.impl.BouquetServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private void showMenu() {
        System.out.println("1 - Show bouquets");
        System.out.println("2 - Show a main bouquet price");
        System.out.println("3 - Sort flowers in a bouquet");
        System.out.println("4 - Find flower in a range");
        System.out.println("0 - Exit");
    }

    public void chooseMenuPoint() {
        BouquetService bouquetService = new BouquetServiceImpl();

        while (true) {
            try {
                Bouquet bouquet = bouquetService.findById(1);
                Scanner scanner = new Scanner(System.in);
                showMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        bouquetService.findBouquets().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println(bouquetService.findById(1).calculateBouquetPrice());
                        break;
                    case 3:
                        FlowerSorter.sortByFreshness(bouquet.getFlowers()).forEach(System.out::println);
                        break;
                    case 4:
                        Optional<CutFlower> flower = FlowerFinder.findFlowerByStemLength(bouquet.getFlowers());
                        if ( flower.isPresent() ) {
                            System.out.println(flower.get());
                        } else {
                            System.out.println("A flower in a range doesn't exist.");
                        }
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default: {
                        System.out.println("Unknown sign, try again.");
                    }
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.INFO, e);
            }
        }
    }
}
