package by.bsac.practical5.task2;

import by.bsac.practical5.exception.FileReaderException;
import by.bsac.practical5.reader.PracticalFileReader;

import java.util.ArrayList;
import java.util.List;

public class SecondTask {
    private static final String FILE_PATH = "src/main/java/by/bsac/practical5/task2/SecondTask.java";

    public static void main(String[] args) {
        try {
            List<String> lines = PracticalFileReader.readFromFile(FILE_PATH);
            List<String> replacedLines = new ArrayList<>();
            lines.forEach(line -> replacedLines.add(line.replace("public", "private")));
            replacedLines.forEach(System.out::println);
        } catch (FileReaderException e) {
            System.out.println(e.getMessage());
        }
    }
}
