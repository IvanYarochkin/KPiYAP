package by.bsac.lab3.exception;

public class LabReaderException extends Exception {
    public LabReaderException() {
    }

    public LabReaderException(String message) {
        super(message);
    }

    public LabReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabReaderException(Throwable cause) {
        super(cause);
    }
}
