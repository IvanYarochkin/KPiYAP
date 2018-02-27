package by.bsac.lab1.task4;

import java.util.Scanner;

public class FourthTask {
    public static void main(String[] args) {
        System.out.println("Enter a number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        if ( number == 0 ) {
            System.out.println(0);
        } else if ( number < 0 ) {
            System.out.println(number + " is a negative number.");
        } else {
            System.out.println(result);
        }
    }
}
