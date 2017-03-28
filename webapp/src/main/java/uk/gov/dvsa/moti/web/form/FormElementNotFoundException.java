package uk.gov.dvsa.moti.web.form;

/**
 * Exception thrown when form element is not found
 */
public class FormElementNotFoundException extends RuntimeException {

    public FormElementNotFoundException(String message) {
        super(message);
    }
}
