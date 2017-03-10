package uk.gov.dvsa.moti.web.fraudSender.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.web.model.FraudModel;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class FraudMapperTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final String FORM_UUID = "ABC-1234-DSA-987";

    @Test
    public void displayForm_returnsView() throws IOException, JAXBException {
        FraudModel model = getModel();

        Fraud xmlFraud = XmlFraudMapper.getXmlFraudModel(model, FORM_UUID);

        assertEquals(xmlFraud.getComments(), model.getComments());
        assertEquals(xmlFraud.getLocationAddress(), model.getLocationAddress());
        assertEquals(xmlFraud.getLocationName(), model.getLocationName());
        assertEquals(xmlFraud.getPersonAddress(), model.getPersonAddress());
        assertEquals(xmlFraud.getPersonName(), model.getPersonName());
        assertEquals(xmlFraud.getVehicleReg(), model.getVehicleReg());
        assertEquals(xmlFraud.getId(), FORM_UUID);
    }

    private FraudModel getModel() throws IOException {
        return MAPPER.readValue(fixture("fixtures/fraud-model.json"), FraudModel.class);
    }
}
