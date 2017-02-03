package uk.gov.dvsa.moti.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.web.resource.SessionResourceInterface;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;
import java.io.IOException;
import java.util.Optional;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class FraudServiceTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final String FORM_UUID = "ABC-1234-DSA-987";

    @Test
    public void displayForm_returnsView() throws IOException {
        FraudService service = new FraudService(createSessionResource(getModel()));

        assertEquals(FraudFormView.class, service.displayForm(FORM_UUID).getClass());
    }

    @Test
    public void validateData_returnsEmptyView_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudFormView> optional = service.validateData(model, FORM_UUID);
        assertFalse(optional.isPresent());
    }

    @Test
    public void validateData_returnsView_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudFormView> optional = service.validateData(model, FORM_UUID);
        assertEquals(FraudFormView.class, optional.get().getClass());
    }

    @Test
    public void displaySummary_returnsView_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudSummaryView> optional = service.displaySummary(FORM_UUID);
        assertEquals(FraudSummaryView.class, optional.get().getClass());
    }

    @Test
    public void displaySummary_returnsEmptyView_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudSummaryView> optional = service.displaySummary(FORM_UUID);
        assertFalse(optional.isPresent());
    }

    @Test
    public void sendReport_returnsTrue_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model));

        assertTrue(service.sendReport(FORM_UUID));
    }

    @Test
    public void sendReport_returnsFalse_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model));

        assertFalse(service.sendReport(FORM_UUID));
    }

    @Test
    public void displaySuccessPage_returnsView_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudSuccessView> optional = service.displaySuccessPage(FORM_UUID);
        assertEquals(FraudSuccessView.class, optional.get().getClass());
    }

    @Test
    public void displaySuccessPage_returnsEmptyView_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model));

        Optional<FraudSuccessView> optional = service.displaySuccessPage(FORM_UUID);
        assertFalse(optional.isPresent());
    }

    private SessionResourceInterface createSessionResource(FraudModel model) {
        SessionResourceInterface sessionResource = mock(SessionResourceInterface.class);
        when(sessionResource.get(FORM_UUID)).thenReturn(model);

        return sessionResource;
    }

    private FraudModel getModel() throws IOException {
        return MAPPER.readValue(fixture("fixtures/fraud-model.json"), FraudModel.class);
    }

    private FraudModel getModelWithInvalidData() throws IOException {
        return MAPPER.readValue(fixture("fixtures/fraud-model-with-invalid-data.json"), FraudModel.class);
    }
}
