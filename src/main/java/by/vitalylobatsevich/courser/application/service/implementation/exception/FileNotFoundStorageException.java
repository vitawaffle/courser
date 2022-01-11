package by.vitalylobatsevich.courser.application.service.implementation.exception;

public class FileNotFoundStorageException extends RuntimeException {

    public FileNotFoundStorageException(final String message) {
        super(message);
    }

    public FileNotFoundStorageException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
