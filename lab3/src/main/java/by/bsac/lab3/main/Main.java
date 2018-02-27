package by.bsac.lab3.main;

import by.bsac.lab3.exception.LabReaderException;
import by.bsac.lab3.reader.LabFileReader;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = LabFileReader.readFile("src/main/resources/lab3.txt");
            lines.forEach(System.out::println);
            System.out.println("\nSorted list:");
            Collections.sort(lines);
            lines.forEach(System.out::println);
        } catch (LabReaderException e) {
            System.out.println(e.getMessage());
        }
    }
}
