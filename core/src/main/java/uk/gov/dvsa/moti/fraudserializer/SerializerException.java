package uk.gov.dvsa.moti.fraudserializer;

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
