package by.bsac.practical5.writer;

import by.bsac.practical5.exception.FileWriterException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PracticalFileWriter {
    public static void write(List<String> lines, String path) throws FileWriterException {
        try {
            Files.write(Paths.get(path), lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new FileWriterException(e);
        }
    }
}
