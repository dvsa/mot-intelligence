package uk.gov.dvsa.moti.processing;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.gov.dvsa.moti.fraudserializer.BatchFraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.FraudSerializer;
import uk.gov.dvsa.moti.fraudserializer.xml.Fraud;
import uk.gov.dvsa.moti.fraudserializer.xml.Root;
import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.persistence.S3Storage;
import uk.gov.dvsa.moti.processing.factory.ExecutorModule;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessorIntegrationTest extends IntegrationTestBase {

    public static final String firstFileComment = "a 1 comment";
    public static final String secondFileComment = "a 2 comment";
    public static final String firstXmlFilename = "xml-1.xml";
    public static final String secondXmlFilename = "xml-2.xml";
    private static FileStorage sourceStorage;
    private static FileStorage destinationStorage;

    @BeforeClass
    public static void setUp() throws IOException {
        setupClass();

        sourceStorage = new S3Storage(
                configuration.getS3Configuration().getSourceBucket().getBucket(),
                configuration.getS3Configuration().getSourceBucket().getRootFolder(),
                configuration.getS3Configuration().getSourceBucket().getAccessKey(),
                configuration.getS3Configuration().getSourceBucket().getSecretKey()
        );

        destinationStorage = new S3Storage(
                configuration.getS3Configuration().getDestinationBucket().getBucket(),
                configuration.getS3Configuration().getDestinationBucket().getRootFolder(),
                configuration.getS3Configuration().getDestinationBucket().getAccessKey(),
                configuration.getS3Configuration().getDestinationBucket().getSecretKey()
        );

        FraudSerializer fraudSerializer = new FraudSerializer();
        Fraud fraud1 = new Fraud().setComments(firstFileComment).setId("1234").setCreateDate(new LocalDate()).setCreateTime(new LocalTime());
        Fraud fraud2 = new Fraud().setComments(secondFileComment).setId("2345").setCreateDate(new LocalDate()).setCreateTime(new LocalTime());

        sourceStorage.store(firstXmlFilename, fraudSerializer.serialize(fraud1));
        sourceStorage.store(secondXmlFilename, fraudSerializer.serialize(fraud2));
    }

    @AfterClass
    public static void tearDown() {
        sourceStorage.delete(firstXmlFilename);
        sourceStorage.delete(secondXmlFilename);
        destinationStorage.delete(configuration.getStorageConfiguration().getDestinationFilePath());
    }

    @Test
    public void testHappyPath() throws IOException {
        String destinationFilePath = "documents.tar.gz";

        configuration.getStorageConfiguration().setDestinationFilePath(destinationFilePath);
        Injector injector = Guice.createInjector(new ExecutorModule(configuration));
        Processor processor = injector.getInstance(Processor.class);
        processor.execute();

        File tgz = destinationStorage.get(destinationFilePath);
        checkIfFileIsCorrect(tgz);
    }

    private void checkIfFileIsCorrect(File tgz) {
        List<File> files = TarDecompressor.unpackTarGzArchive(tgz.getContent());
        List<String> fileList = files.stream().map(file -> file.getPath()).collect(Collectors.toList());
        Assert.assertEquals(true, fileList.contains("documents-manifest.txt"));

        Root xmlDoc = new BatchFraudSerializer().unserialize(files.get(1).getContentAsString());
        Assert.assertEquals(xmlDoc.getData().getRows().get(0).getComments(), firstFileComment);
        Assert.assertEquals(xmlDoc.getData().getRows().get(1).getComments(), secondFileComment);
    }
}
