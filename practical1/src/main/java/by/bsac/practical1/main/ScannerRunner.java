package by.bsac.practical1.main;

import java.util.Scanner;

public class ScannerRunner {
    private static final String NAME_MESSAGE = "Enter your name and press <Enter>";
    private static final String HELLO_MESSAGE = "Hello ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(NAME_MESSAGE);
        String name = scanner.nextLine();
        System.out.println(HELLO_MESSAGE + name);
    }
}
