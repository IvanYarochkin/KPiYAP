package by.bsac.practical2.task1;

/**
 * This task finds even numbers in an array
 */
public class FirstTask {
    public static void main(String[] args) {
        int[] numbers = new int[15];
        int evenNumbers = 0;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 9);
            if ( numbers[i] != 0 && numbers[i] % 2 == 0 ) {
                evenNumbers++;
            }
        }

        for (double number : numbers) {
            System.out.println(number);
        }

        System.out.println(evenNumbers);
    }
}
