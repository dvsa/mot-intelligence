package uk.gov.dvsa.moti.processing.executor;

import com.google.inject.Inject;

import net.logstash.logback.marker.Markers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.processing.configuration.model.StorageConfiguration;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Download documents from source storage and store them in destination storage
 */
public class DocumentStorage {
    private FileStorage downloader;
    private FileStorage uploader;
    private StorageConfiguration storageConfiguration;
    private static Logger logger = LoggerFactory.getLogger(DocumentStorage.class);

    @Inject
    public DocumentStorage(FileStorage downloader, FileStorage uploader, StorageConfiguration storageConfiguration) {
        this.downloader = downloader;
        this.uploader = uploader;
        this.storageConfiguration = storageConfiguration;
    }

    /**
     * Download files from storage
     * @return
     */
    public List<File> downloadFiles() {
        logger.info("Starting file download...");
        String prefix = null != storageConfiguration.getDownloadedFilesPrefix() ? storageConfiguration.getDownloadedFilesPrefix() : "";
        List<File> fileList = downloader.getMultiple(
                prefix,
                storageConfiguration.getMaxDownloadedFilesLimit()
        );
        logger.info(String.format("Downloaded %s files", fileList.size()));

        return fileList;
    }

    /**
     * Upload files to storage
     * @param fileContent
     */
    public void uploadFile(byte[] fileContent) {
        String destinationFilePath = storageConfiguration.getDestinationFilePath();
        destinationFilePath = formatDestinationFilePath(destinationFilePath);
        logger.info(Markers.append("context", new HashMap.SimpleEntry<>("filename", destinationFilePath)), "Uploading file");
        uploader.store(destinationFilePath, fileContent);
        logger.info(Markers.append("context", new HashMap.SimpleEntry<>("filename", destinationFilePath)), "File uploaded successfully");
    }

    /**
     * Format file path
     * @param destinationFilePath
     * @return
     */
    private String formatDestinationFilePath(String destinationFilePath) {
        return (new FilePathFormatter()).format(destinationFilePath);
    }

    /**
     * Delete many files from storage
     * @param files
     */
    public void deleteFiles(List<File> files) {
        ArrayList<String> filenames = new ArrayList<>();
        for (File file : files) {
            logger.info(Markers.append("context", new HashMap.SimpleEntry<>("filename", file.getPath())), "File is going to be deleted");
            filenames.add(file.getPath());
        }

        logger.info(String.format("Deleting %s files", files.size()));
        downloader.delete(filenames);
        logger.info(filenames.size() + " files were removed");
    }
}
