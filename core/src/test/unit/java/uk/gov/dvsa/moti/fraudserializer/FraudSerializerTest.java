package uk.gov.dvsa.moti.fraudserializer;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.gov.dvsa.moti.FraudHelper;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class FraudSerializerTest {

    @DataProvider
    public static Object[][] dataProviderFraud() {
        return new Object[][]{
                {FraudHelper.createFraud()},
                {FraudHelper.createEmptyFraud()}
        };
    }

    @Test
    @UseDataProvider("dataProviderFraud")
    public void testFraudIsSerialized(Fraud input) throws IOException, JAXBException {
        FraudSerializer fraudSerializer = new FraudSerializer();

        String result = fraudSerializer.serialize(input);
        Fraud output = fraudSerializer.unserialize(result);

        assertEquals(input, output);
    }
}
