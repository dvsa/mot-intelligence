package uk.gov.dvsa.moti.processing;

import com.google.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDeleterInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDownloaderInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentProcessorInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentUploaderInterface;
import uk.gov.dvsa.moti.processing.executor.ProcessorException;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Processor {
    private final DocumentDownloaderInterface documentDownloader;
    private final DocumentProcessorInterface documentProcessor;
    private final DocumentUploaderInterface documentUploader;
    private final DocumentDeleterInterface documentDeleter;
    private static Logger logger = LoggerFactory.getLogger(Processor.class);

    @Inject
    public Processor(
            DocumentDownloaderInterface documentDownloader,
            DocumentProcessorInterface documentProcessor,
            DocumentUploaderInterface documentUploader,
            DocumentDeleterInterface documentDeleter
    ) {
        this.documentDownloader = documentDownloader;
        this.documentProcessor = documentProcessor;
        this.documentUploader = documentUploader;
        this.documentDeleter = documentDeleter;
    }

    public void execute() {
        try {
            List<File> downloadedDocs = documentDownloader.downloadFiles();
            if(CollectionUtils.isEmpty(downloadedDocs)){
                logger.info("No files to process, exiting...");
                return;
            }

            ByteArrayOutputStream outputDocument = documentProcessor.processFiles(downloadedDocs);
            documentUploader.uploadDocument(outputDocument);
            documentDeleter.deleteDocuments(downloadedDocs);
        } catch (Exception e) {
            logger.error("Failed to run processor", e);
            throw new ProcessorException("Failed to run processor", e);
        }
    }
}
