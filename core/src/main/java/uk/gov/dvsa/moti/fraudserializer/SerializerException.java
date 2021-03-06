package uk.gov.dvsa.moti.fraudserializer;

/**
 * Exception thrown by FraudSerializer and BatchFraudSerializer
 */
public class SerializerException extends RuntimeException {
    public SerializerException() {
        super();
    }

    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
