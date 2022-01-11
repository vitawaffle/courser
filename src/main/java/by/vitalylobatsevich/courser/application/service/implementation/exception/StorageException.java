package by.vitalylobatsevich.courser.application.service.implementation.exception;

public class StorageException extends RuntimeException {

    public StorageException(final String message) {
        super(message);
    }

    public StorageException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
