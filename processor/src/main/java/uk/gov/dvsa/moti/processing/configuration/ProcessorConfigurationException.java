package uk.gov.dvsa.moti.processing.configuration;

public class ProcessorConfigurationException extends RuntimeException {
    public ProcessorConfigurationException() {
        super();
    }

    public ProcessorConfigurationException(String message) {
        super(message);
    }

    public ProcessorConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
