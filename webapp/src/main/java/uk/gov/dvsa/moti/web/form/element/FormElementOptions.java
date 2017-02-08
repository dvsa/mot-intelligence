package uk.gov.dvsa.moti.web.form.element;

public class FormElementOptions {
    private String id = "";
    private String label = "";
    private String hint = "";
    private String cssClass = "";
    private boolean isRequired = true;

    public String getId() {
        return id;
    }

    public FormElementOptions setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public FormElementOptions setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getHint() {
        return hint;
    }

    public FormElementOptions setHint(String hint) {
        this.hint = hint;
        return this;
    }

    public String getCssClass() {
        return cssClass;
    }

    public FormElementOptions setCssClass(String cssClass) {
        this.cssClass = cssClass;
        return this;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public FormElementOptions markAsOptional() {
        isRequired = false;
        return this;
    }
}
