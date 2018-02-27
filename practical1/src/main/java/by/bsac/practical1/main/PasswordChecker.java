package by.bsac.practical1.main;

import java.util.Scanner;

public class PasswordChecker {
    private static final String RIGHT_PASSWORD = "TEST";
    private static final String PASSWORD_MESSAGE = "Enter your password and press <Enter>";
    private static final String PASSWORD_RIGHT_MESSAGE = "Your password is right";
    private static final String PASSWORD_WRONG_MESSAGE = "Your password is wrong";

    public static void main(String[] args) {
        System.out.println(PASSWORD_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        if ( RIGHT_PASSWORD.equals(password.toUpperCase()) ) {
            System.out.println(PASSWORD_RIGHT_MESSAGE);
        } else {
            System.out.println(PASSWORD_WRONG_MESSAGE);
        }
    }
}
