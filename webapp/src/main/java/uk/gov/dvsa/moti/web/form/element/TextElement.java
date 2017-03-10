package uk.gov.dvsa.moti.web.form.element;

public class TextElement extends AbstractFormElement {

    public TextElement(String name, String value) {
        super(name, value);
    }

    public ElementType getType() {
        return ElementType.TEXT;
    }
}
