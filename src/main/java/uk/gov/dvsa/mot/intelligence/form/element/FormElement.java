package uk.gov.dvsa.mot.intelligence.form.element;

public class FormElement {
    private String name;
    private String value;
    private String errorMessage;

    public FormElement(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public FormElement setValue(String value) {
        this.value = value;

        return this;
    }

    public String getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String setErrorMessage(String errorMessage) {
        return this.errorMessage = errorMessage;
    }
}
