package by.bsac.practical5.task1;

import by.bsac.practical5.exception.FileReaderException;
import by.bsac.practical5.exception.FileWriterException;
import by.bsac.practical5.reader.PracticalFileReader;
import by.bsac.practical5.writer.PracticalFileWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstTask {
    private static final String FILE_PATH = "src/main/resources/task1.txt";

    public static void main(String[] args) {
        try {
            List<String> numbers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                numbers.add(String.valueOf(Math.round(Math.random() * 10)));
            }
            PracticalFileWriter.write(numbers, FILE_PATH);
            List<String> lines = PracticalFileReader.readFromFile(FILE_PATH);
            Collections.sort(lines);
            PracticalFileWriter.write(lines, FILE_PATH);
        } catch (FileReaderException | FileWriterException e) {
            System.out.println(e.getMessage());
        }
    }
}
