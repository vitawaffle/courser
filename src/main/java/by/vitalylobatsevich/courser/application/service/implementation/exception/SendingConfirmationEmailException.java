package by.vitalylobatsevich.courser.application.service.implementation.exception;

public class SendingConfirmationEmailException extends RuntimeException {

    public SendingConfirmationEmailException(final String message) {
        super(message);
    }

    public SendingConfirmationEmailException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
