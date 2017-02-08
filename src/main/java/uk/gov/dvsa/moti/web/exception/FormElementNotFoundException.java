package uk.gov.dvsa.moti.web.exception;

public class FormElementNotFoundException extends RuntimeException {

    public FormElementNotFoundException(String message) {
        super(message);
    }
}
