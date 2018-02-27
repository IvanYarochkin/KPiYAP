package by.bsac.practical4.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAction {
    private static final Pattern pattern = Pattern.compile("\\w+");

    public static void showWordsWithMaxAndMinLength(String sourceText) {
        String[] text = sourceText.split("\\s");
        text = deleteSigns(text);

        int minLength = text != null && text[0] != null ? text[0].length() : 0;
        int maxLength = 0;

        for (String word : text) {
            if ( word != null && word.length() > maxLength ) {
                maxLength = word.length();
            }

            if ( word != null && word.length() < minLength ) {
                minLength = word.length();
            }
        }

        System.out.println("Max length = " + maxLength);
        System.out.println("Min length = " + minLength);

        System.out.println("\nWords having a max length:");
        for (String word : text) {
            if ( word != null && word.length() == maxLength ) {
                System.out.println(word);
            }
        }

        System.out.println("\nWords having a min length:");
        for (String word : text) {
            if ( word != null && word.length() == minLength ) {
                System.out.println(word);
            }
        }
    }

    private static String[] deleteSigns(String[] text) {
        String[] words = new String[text.length];
        for (int i = 0; i < text.length; i++) {
            Matcher matcher = pattern.matcher(text[i]);
            while (matcher.find()) {
                words[i] = matcher.group();
            }
        }
        return words;
    }
}
