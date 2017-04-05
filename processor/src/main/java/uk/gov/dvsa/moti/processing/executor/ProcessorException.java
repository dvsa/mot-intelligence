package uk.gov.dvsa.moti.processing.executor;

/**
 * Exception thrown in processor
 */
public class ProcessorException extends RuntimeException {
    public ProcessorException() {
        super();
    }

    public ProcessorException(String message) {
        super(message);
    }

    public ProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
