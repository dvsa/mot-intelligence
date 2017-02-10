package uk.gov.dvsa.moti.web.persistence;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.gov.dvsa.moti.web.IntegrationTestBase;

import java.io.IOException;

public class S3Test extends IntegrationTestBase {

    @BeforeClass
    public static void setUp() throws IOException {
        setupClass();
    }

    @Test
    public void returnsValidationErrorWithValidData() throws IOException {
        Assert.assertEquals("mot-intelligence-fb",configuration.getS3Configuration().getPrivateBucketConfiguration().getBucket());
    }
}
