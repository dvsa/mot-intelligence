package uk.gov.dvsa.moti.web.form;

import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract representation of form
 */
public abstract class AbstractForm {

    protected List<AbstractFormElement> formElements = new ArrayList<>();

    protected static Validator validator;

    public AbstractForm() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public abstract boolean isValid();

    protected void addElement(AbstractFormElement element) {
        formElements.add(element);
    }

    public AbstractFormElement getElement(String name) {

        return formElements
                .stream()
                .filter(element -> name.equals(element.getName()))
                .findAny()
                .orElseThrow(() -> new FormElementNotFoundException("Form element with name '" + name + "' not found"));
    }

    public List<AbstractFormElement> getElements() {
        return formElements;
    }
}
