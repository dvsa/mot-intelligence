package uk.gov.dvsa.moti.web.persistence;


import com.amazonaws.services.s3.model.AmazonS3Exception;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.S3Storage;
import uk.gov.dvsa.moti.web.IntegrationTestBase;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class S3Test extends IntegrationTestBase {

    @BeforeClass
    public static void setUp() throws IOException {
        setupClass();
    }

    @Test
    public void returnsValidationErrorWithValidData() throws IOException {
        String bucket = configuration.getS3Configuration().getBucket();
        String rootFolder = configuration.getS3Configuration().getRootFolder();

        FileStorage storage = new S3Storage(bucket, rootFolder);

        String expectedMessage1 = "Sample Message";
        String path1 = "sample-folder/testFile.txt";

        String expectedMessage2 = "Sample Message";
        String path2 = "sample-folder/totally-different-testFile.txt";

        storage.store(path1, expectedMessage1);
        storage.store(new File(path2, expectedMessage2));

        List<File> files = storage.getMultiple("sample-folder", 10);

        File receivedFile1 = files.get(0);
        File receivedFile2 = files.get(1);

        Assert.assertEquals(expectedMessage1, receivedFile1.getContentAsString());
        Assert.assertEquals(path1, receivedFile1.getPath());

        Assert.assertEquals(expectedMessage2, receivedFile2.getContentAsString());
        Assert.assertEquals(path2, receivedFile2.getPath());

        storage.delete(files.stream().map(file -> file.getPath()).collect(Collectors.toList()));

        try {
            storage.get(path1);
            Assert.fail("Exception was exptected.");
        } catch(AmazonS3Exception ex) {
            Assert.assertEquals("NoSuchKey", ex.getErrorCode());
        }
    }
}
