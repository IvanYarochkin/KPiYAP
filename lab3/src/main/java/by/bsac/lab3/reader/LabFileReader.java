package by.bsac.lab3.reader;

import by.bsac.lab3.exception.LabReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LabFileReader {
    public static List<String> readFile(String path) throws LabReaderException {
        try {
            if ( Files.exists(Paths.get(path)) ) {
                return Files.lines(Paths.get(path)).collect(Collectors.toList());
            } else {
                throw new LabReaderException("File having path \"" + path + "\" doesn't exist");
            }
        } catch (IOException e) {
            throw new LabReaderException(e);
        }
    }
}
