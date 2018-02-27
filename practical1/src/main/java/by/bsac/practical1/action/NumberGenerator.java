package by.bsac.practical1.action;

import java.util.ArrayList;
import java.util.List;

public class NumberGenerator {
    public static List<Double> generateNumber(int amount) {
        List<Double> randomNumbers = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            randomNumbers.add((Math.random() * 10));
        }
        return randomNumbers;
    }
}
