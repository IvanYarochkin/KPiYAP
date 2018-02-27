package by.bsac.practical5.task3;

import by.bsac.practical5.exception.FileReaderException;
import by.bsac.practical5.exception.FileWriterException;
import by.bsac.practical5.reader.PracticalFileReader;
import by.bsac.practical5.writer.PracticalFileWriter;

import java.util.ArrayList;
import java.util.List;

public class ThirdTask {
    private static final String JAVA_FILE_PATH = "src/main/java/by/bsac/practical5/task3/ThirdTask.java";
    private static final String RESULT_FILE_PATH = "src/main/resources/task3.txt";

    public static void main(String[] args) {
        try {
            List<String> lines = PracticalFileReader.readFromFile(JAVA_FILE_PATH);
            List<String> reverseddLines = new ArrayList<>();
            lines.forEach(line -> {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(line);
                stringBuilder.reverse();
                reverseddLines.add(stringBuilder.substring(0));
            });
            PracticalFileWriter.write(reverseddLines, RESULT_FILE_PATH);
        } catch (FileReaderException | FileWriterException e) {
            System.out.println(e.getMessage());
        }
    }
}
