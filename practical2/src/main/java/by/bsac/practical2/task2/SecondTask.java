package by.bsac.practical2.task2;

import java.util.Scanner;

/**
 * This task rounds all real numbers in an array
 */
public class SecondTask {
    public static void main(String[] args) {
        System.out.println("Enter an array size: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[][] array = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = (Math.random() * 10);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("array["+i+"]["+j+"] = " + array[i][j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = Math.round(array[i][j]);
                System.out.println("array["+i+"]["+j+"] = " + array[i][j]);
            }
        }

    }
}
