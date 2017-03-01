package uk.gov.dvsa.moti.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.web.model.FraudModel;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.FileStorageException;
import uk.gov.dvsa.moti.web.fraudSender.FraudSender;
import uk.gov.dvsa.moti.web.fraudSender.XmlFraudSender;
import uk.gov.dvsa.moti.web.resource.SessionResourceInterface;
import uk.gov.dvsa.moti.web.views.FraudFormView;
import uk.gov.dvsa.moti.web.views.FraudSuccessView;
import uk.gov.dvsa.moti.web.views.FraudSummaryView;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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
        FraudService service = new FraudService(createSessionResource(getModel()), getFraudSenderServiceCorrect());

        assertEquals(FraudFormView.class, service.displayForm().getClass());
    }

    @Test
    public void validateData_returnsEmptyView_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudFormView> optional = service.validateData(model);
        assertFalse(optional.isPresent());
    }

    @Test
    public void validateData_returnsView_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudFormView> optional = service.validateData(model);
        assertEquals(FraudFormView.class, optional.get().getClass());
    }

    @Test
    public void displaySummary_returnsView_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudSummaryView> optional = service.displaySummary();
        assertEquals(FraudSummaryView.class, optional.get().getClass());
    }

    @Test
    public void displaySummary_returnsEmptyView_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudSummaryView> optional = service.displaySummary();
        assertFalse(optional.isPresent());
    }

    @Test
    public void sendReport_returnsTrue_whenModelDataIsValid() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        assertTrue(service.sendReport(FORM_UUID));
    }

    @Test
    public void sendReport_returnsFalse_whenModelDataIsInvalid() throws IOException {
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        assertFalse(service.sendReport(FORM_UUID));
    }

    @Test
    public void displaySuccessPage_returnsView_whenModelDataIsValid() throws IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudSuccessView> optional = service.displaySuccessPage(resp);
        assertEquals(FraudSuccessView.class, optional.get().getClass());
    }

    @Test
    public void displaySuccessPage_returnsEmptyView_whenModelDataIsInvalid() throws IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FraudModel model = getModelWithInvalidData();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        Optional<FraudSuccessView> optional = service.displaySuccessPage(resp);
        assertFalse(optional.isPresent());
    }

    @Test
    public void validateData_returnsUpdatedForm_whenModelDataIsSend() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceCorrect());

        assertTrue(service.sendReport(FORM_UUID));
    }

    @Test(expected=FileStorageException.class)
    public void validateData_returnsError_whenModelDataIsNotSend() throws IOException {
        FraudModel model = getModel();
        FraudService service = new FraudService(createSessionResource(model), getFraudSenderServiceThrowS3Error());

        service.sendReport(FORM_UUID);
    }

    private SessionResourceInterface createSessionResource(FraudModel model) {
        SessionResourceInterface sessionResource = mock(SessionResourceInterface.class);
        when(sessionResource.get()).thenReturn(model);

        return sessionResource;
    }

    private FraudModel getModel() throws IOException {
        return MAPPER.readValue(fixture("fixtures/fraud-model.json"), FraudModel.class);
    }

    private FraudSender getFraudSenderServiceCorrect() throws IOException {
        return new XmlFraudSender(getFileStoreCorrect(), getFraudSerializer());
    }

    private FraudSender getFraudSenderServiceThrowS3Error() throws IOException {
        return new XmlFraudSender(getFileStoreThrowS3Error(), getFraudSerializer());
    }

    private FraudSerializer getFraudSerializer() {
        FraudSerializer fraudSerializer = mock(FraudSerializer.class);
        doReturn("serialized").when(fraudSerializer).serialize(any(Fraud.class));

        return  fraudSerializer;
    }

    private FileStorage getFileStoreCorrect() {
        FileStorage fileStorage = mock(FileStorage.class);
        doNothing().when(fileStorage).store(anyString(), anyString());

        return fileStorage;
    }

    private FileStorage getFileStoreThrowS3Error() throws IOException {
        FileStorage fileStorage = mock(FileStorage.class);
        doThrow(new FileStorageException()).when(fileStorage).store(anyString(), anyString());
        return fileStorage;
    }

    private FraudModel getModelWithInvalidData() throws IOException {
        return MAPPER.readValue(fixture("fixtures/fraud-model-with-invalid-data.json"), FraudModel.class);
    }
}
