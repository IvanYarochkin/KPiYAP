package by.bsac.lab1.task3;

public class ThirdTask {
    public static void main(String[] args) {
        int firstNumber = 1;
        int secondNumber = 1;
        System.out.println(firstNumber);
        for (int i = 0; i < 30; i++) {
            System.out.println(secondNumber);
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = firstNumber + temp;
        }
    }
}
