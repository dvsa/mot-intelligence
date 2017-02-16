package uk.gov.dvsa.moti.fraudserializer;

import org.junit.Test;
import uk.gov.dvsa.moti.FraudHelper;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FraudSerializerTest {

    @Test
    public void testFraudIsSerialized() throws IOException, JAXBException {
        Fraud input = createFraudModel();

        FraudSerializer fraudSerializer = new FraudSerializer();
        String result = fraudSerializer.serialize(input);

        Fraud output = fraudSerializer.unserialize(result);

        assertEquals(input, output);
    }

    private Fraud createFraudModel() throws IOException {
        return FraudHelper.createFraud();
    }
}
