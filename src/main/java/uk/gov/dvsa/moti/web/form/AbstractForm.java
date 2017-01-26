package uk.gov.dvsa.moti.web.form;

import uk.gov.dvsa.moti.web.form.element.FormElement;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;

public abstract class AbstractForm {

    protected HashMap<String, FormElement> formElements = new HashMap<>();

    protected static Validator validator;

    public AbstractForm() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public abstract boolean isValid();

    protected void addElement(String name, String value) {
        formElements.put(name, new FormElement(name, value));
    }

    public FormElement getElement(String name) {
        return formElements.get(name);
    }
}
