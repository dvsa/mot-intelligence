package uk.gov.dvsa.moti.fraudserializer;

import org.junit.Test;
import uk.gov.dvsa.moti.FraudHelper;
import uk.gov.dvsa.moti.fraudserializer.xml.Root;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BatchFraudSerializerTest {

    @Test
    public void testBatchFraudIsSerialized() {
        BatchFraudSerializer serializer = new BatchFraudSerializer();

        List<Fraud> input = createFrauds();
        String result = serializer.serialize(input);
        Root output = serializer.unserialize(result);

        assertEquals(input, output.getData().getRows());
    }

    private List<Fraud> createFrauds() {
        List<Fraud> frauds = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            frauds.add(createFraud());
        }
        return frauds;
    }

    private Fraud createFraud() {
        return FraudHelper.createFraud();
    }
}