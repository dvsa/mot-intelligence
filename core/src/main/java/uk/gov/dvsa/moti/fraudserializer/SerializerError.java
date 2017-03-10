package uk.gov.dvsa.moti.fraudserializer;

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
