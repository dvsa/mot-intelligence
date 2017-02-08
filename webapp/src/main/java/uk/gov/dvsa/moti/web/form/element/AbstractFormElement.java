package uk.gov.dvsa.moti.web.form.element;

public abstract class AbstractFormElement {
    private String name = "";
    private String value = "";
    private String errorMessage = "";
    private FormElementOptions options = new FormElementOptions();

    public AbstractFormElement(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AbstractFormElement setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

        return this;
    }

    public AbstractFormElement setOptions(FormElementOptions options) {
        this.options = options;

        return this;
    }

    public FormElementOptions getOptions() {
        return options;
    }

    public abstract ElementType getType();
}
