package uk.gov.dvsa.moti.configuration;

public class ConfigurationException extends RuntimeException {
    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}