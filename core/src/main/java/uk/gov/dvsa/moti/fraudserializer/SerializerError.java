package uk.gov.dvsa.moti.fraudserializer;

/**
 * Helper class for error messages re-use
 */
public enum SerializerError {
    UNSERIALIZE("Unable to unserialize"),

    SERIALIZE("Unable to serialize");

    private String message;

    SerializerError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
