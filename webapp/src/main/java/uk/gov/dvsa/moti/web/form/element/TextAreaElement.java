package uk.gov.dvsa.moti.web.form.element;

public class TextAreaElement extends AbstractFormElement {
    public TextAreaElement(String name, String value) {
        super(name, value);
    }

    public ElementType getType() {
        return ElementType.TEXTAREA;
    }
}
