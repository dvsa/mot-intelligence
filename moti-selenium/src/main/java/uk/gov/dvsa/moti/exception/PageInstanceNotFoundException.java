package uk.gov.dvsa.moti.exception;

public class PageInstanceNotFoundException extends RuntimeException {

    public PageInstanceNotFoundException(final String message) {
        super(message);
    }

    public PageInstanceNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
