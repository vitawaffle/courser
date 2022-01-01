package by.vitalylobatsevich.courser.application.service.implementation.exception;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(final String message) {
        super(message);
    }

    public StorageFileNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
