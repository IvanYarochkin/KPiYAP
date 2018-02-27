package by.bsac.practical5.reader;

import by.bsac.practical5.exception.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PracticalFileReader {
    public static List<String> readFromFile(String path) throws FileReaderException {
        try {
            if ( Files.exists(Paths.get(path)) ) {
                return Files.lines(Paths.get(path)).collect(Collectors.toList());
            } else {
                throw new FileReaderException("File having path \"" + path + "\" doesn't exist");
            }
        } catch (IOException e) {
            throw new FileReaderException(e);
        }
    }
}
