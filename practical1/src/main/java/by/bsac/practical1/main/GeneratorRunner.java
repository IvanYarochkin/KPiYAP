package by.bsac.practical1.main;

import by.bsac.practical1.action.NumberGenerator;

import java.util.List;

public class GeneratorRunner {
    private static final String SPACE = " ";

    public static void main(String[] args) {
        List<Double> numbers = NumberGenerator.generateNumber(5);
        numbers.forEach(System.out::println);
        for (int i = 0; i < numbers.size(); i++) {
            if ( i % 2 == 0 ) {
                System.out.print(numbers.get(i) + SPACE);
            } else {
                System.out.println(numbers.get(i));
            }
        }
    }
}
