package uk.gov.dvsa.moti.processing;

import org.junit.Test;
import org.mockito.Mockito;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.persistence.FileStorageException;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDeleter;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDownloader;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentProcessor;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentUploader;
import uk.gov.dvsa.moti.processing.executor.DocumentStorage;
import uk.gov.dvsa.moti.processing.step.DocumentDeleteStep;
import uk.gov.dvsa.moti.processing.step.DocumentsDownloadStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.util.ArrayList;

public class ProcessorTest {

    @Test
    public void testRetrying(){
        DocumentStorage documentStorage = Mockito.mock(DocumentStorage.class);
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("asdasd", "asd"));
        Mockito.when(documentStorage.downloadFiles()).thenThrow(FileStorageException.class).thenReturn(files);

        Mockito
                .doThrow(Exception.class)
                .doThrow(Exception.class)
                .doNothing()
                .when(documentStorage).deleteFiles(Mockito.anyListOf(File.class));

        Processor processor;
        processor = new Processor(
                new DocumentDownloader(new DocumentsDownloadStep(documentStorage), new StepRunner()),
                Mockito.mock(DocumentProcessor.class),
                Mockito.mock(DocumentUploader.class),
                new DocumentDeleter(new DocumentDeleteStep(documentStorage), new StepRunner())
        );
        processor.execute();

        Mockito.verify(documentStorage, Mockito.times(2)).downloadFiles();
        Mockito.verify(documentStorage, Mockito.times(3)).deleteFiles(Mockito.anyList());
    }
}