package uk.gov.dvsa.moti.processing;

import org.junit.Assert;
import org.junit.Test;

import uk.gov.dvsa.moti.fraudserializer.BatchFraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.executor.DocumentJoiner;

import java.util.ArrayList;

public class DocumentJoinerTest {

    @Test
    public void testJoining(){
        DocumentJoiner documentJoiner = new DocumentJoiner(new BatchFraudSerializer(), new FraudSerializer());
        FraudSerializer fraudSerializer = new FraudSerializer();

        String text1 = "unique text";
        String text2 = "somecontent";

        Fraud fraud1 = new Fraud().setComments(text1).setId("1234");
        Fraud fraud2 = new Fraud().setComments(text2).setId("2345");

        File file = new File("nevermind", fraudSerializer.serialize(fraud1));
        File file2 = new File("nevermind", fraudSerializer.serialize(fraud2));

        ArrayList<File> files = new ArrayList<>();
        files.add(file);
        files.add(file2);

        String joinFiles = documentJoiner.joinFiles(files);
        Assert.assertTrue(joinFiles.contains(text1));
        Assert.assertTrue(joinFiles.contains(text2));
    }
}
