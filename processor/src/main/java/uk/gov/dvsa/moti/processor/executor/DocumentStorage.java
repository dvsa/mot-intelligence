package uk.gov.dvsa.moti.processor.executor;

import com.google.inject.Inject;

import net.logstash.logback.marker.Markers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.persistence.FileStorage;
import uk.gov.dvsa.moti.processor.configuration.model.StorageConfiguration;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void uploadFile(ByteArrayOutputStream outputStream) {
        String destinationFilePath = storageConfiguration.getDestinationFilePath();
        logger.info(Markers.append("context", new HashMap.SimpleEntry<>("filename", destinationFilePath)), "Uploading file");
        uploader.store(destinationFilePath, outputStream.toByteArray());
        logger.info(Markers.append("context", new HashMap.SimpleEntry<>("filename", destinationFilePath)), "File uploaded successfully");
    }

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
