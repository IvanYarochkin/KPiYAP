package by.bsac.practical1.main;

public class ArgumentsPrinter {
    private static final String ARGUMENT_MESSAGE = "Argument ";
    private static final String EQUALS_MESSAGE = " = ";
    private static final String SUM_MESSAGE = "Arguments sum =  ";
    private static final String COMPOSITION_MESSAGE = "Arguments composition = ";

    public static void main(String[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.println(ARGUMENT_MESSAGE + i + EQUALS_MESSAGE + args[i]);
        }

        System.out.println(SUM_MESSAGE + countArgumentSum(args));

        System.out.println(COMPOSITION_MESSAGE + countArgumentComposition(args));
    }

    private static int countArgumentSum(String[] args) {
        int sum = 0;
        for (String argument : args) {
            sum += Integer.parseInt(argument);
        }
        return sum;
    }

    private static int countArgumentComposition(String[] args) {
        int composition = 1;
        for (String argument : args) {
            composition *= Integer.parseInt(argument);
        }
        return composition;
    }
}
