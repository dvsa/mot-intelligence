package uk.gov.dvsa.moti.web.filter;

/**
 * Exception thrown in filters
 */
public class FilterException extends RuntimeException {
    public FilterException() {
        super();
    }

    public FilterException(String message) {
        super(message);
    }


    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }
}
