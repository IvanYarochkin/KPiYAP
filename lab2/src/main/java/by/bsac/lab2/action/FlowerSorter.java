package by.bsac.lab2.action;

import by.bsac.lab2.entity.CutFlower;

import java.util.Comparator;
import java.util.List;

public class FlowerSorter {
    public static List<CutFlower> sortByFreshness(List<CutFlower> flowers) {
        Comparator<CutFlower> comparator = (firstFlower, secondFlower) -> {
            if ( firstFlower.getFreshness() > secondFlower.getFreshness() ) {
                return 1;
            }
            if ( firstFlower.getFreshness() < secondFlower.getFreshness() ) {
                return -1;
            } else {
                return 0;
            }
        };
        flowers.sort(comparator);
        return flowers;
    }
}
