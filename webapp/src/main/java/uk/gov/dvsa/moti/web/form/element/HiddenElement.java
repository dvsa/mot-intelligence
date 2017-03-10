package uk.gov.dvsa.moti.web.form.element;

public class HiddenElement extends AbstractFormElement {

    public HiddenElement(String name, String value) {
        super(name, value);
    }

    @Override
    public ElementType getType() {
        return ElementType.HIDDEN;
    }
}
