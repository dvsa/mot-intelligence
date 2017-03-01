package uk.gov.dvsa.moti.web.form;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;
import uk.gov.dvsa.moti.web.model.FraudModel;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.*;

public class FraudFormTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void noValidationErrorWithValidData() throws IOException {
        FraudForm form = createForm();

        assertTrue(form.isValid());

        form.getElements().forEach((element)->{
            assertTrue(element.getErrorMessage().isEmpty());
        });
    }

    @Test
    public void returnsValidationErrorWithValidData() throws IOException {
        FraudModel model = MAPPER.readValue(fixture("fixtures/fraud-model-with-invalid-data.json"), FraudModel.class);
        FraudForm form = new FraudForm(model);

        assertFalse(form.isValid());

        form.getElements().forEach((element)->{
            assertFalse(element.getErrorMessage().isEmpty());
        });
    }

    @Test
    public void returnsCorrectNumberOfFormElements() throws IOException{
        FraudForm form = createForm();

        assertEquals(6, form.getElements().size());
    }

    @Test
    public void returnsFormElement() throws IOException {
        FraudForm form = createForm();
        AbstractFormElement element = form.getElement(FraudModel.PARAM_LOCATION_NAME);

        assertEquals(FraudModel.PARAM_LOCATION_NAME, element.getName());
    }

    @Test
    public void throwsExceptionWhenNotFoundFormElement() throws IOException {
        thrown.expect(FormElementNotFoundException.class);

        FraudForm form = createForm();
        form.getElement("not existing element");
    }

    private FraudForm createForm() throws IOException{
        FraudModel model = MAPPER.readValue(fixture("fixtures/fraud-model.json"), FraudModel.class);
        return new FraudForm(model);
    }
}
