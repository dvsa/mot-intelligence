package uk.gov.dvsa.moti.web.persistence;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.S3Storage;
import uk.gov.dvsa.moti.web.IntegrationTestBase;

import java.io.IOException;

public class S3Test extends IntegrationTestBase {

    @BeforeClass
    public static void setUp() throws IOException {
        setupClass();
    }

    @Test
    public void returnsValidationErrorWithValidData() throws IOException {
        String bucket = configuration.getS3Configuration().getPrivateBucketConfiguration().getBucket();
        String rootFolder = configuration.getS3Configuration().getPrivateBucketConfiguration().getRootFolder();
        String accessKey = configuration.getS3Configuration().getPrivateBucketConfiguration().getAccessKey();
        String secretKey = configuration.getS3Configuration().getPrivateBucketConfiguration().getSecretKey();

        FileStorage storage = new S3Storage(bucket, rootFolder, accessKey, secretKey);

        String expectedMessage = "Sample Message";

        storage.store("testFile.txt", expectedMessage);

        String actualMessage = storage.get("testFile.txt");

        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
