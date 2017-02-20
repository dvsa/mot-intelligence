package uk.gov.dvsa.moti.processor.executor;

import com.google.inject.Inject;

import net.logstash.logback.marker.Markers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.persistence.File;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Executor {
    private final int MAX_ITERATION_COUNT = 3;
    private ManifestCreator manifestCreator;
    private DocumentStorage documentStorage;
    private DocumentCompressorInterface compressor;
    private DocumentJoiner documentJoiner;
    private static Logger logger = LoggerFactory.getLogger(Executor.class);

    @Inject
    public Executor(
            DocumentStorage documentStorage,
            ManifestCreator manifestCreator,
            DocumentJoiner documentJoiner,
            DocumentCompressorInterface compressor
    ) {
        this.documentStorage = documentStorage;
        this.manifestCreator = manifestCreator;
        this.documentJoiner = documentJoiner;
        this.compressor = compressor;
    }

    public void execute() {
        List<File> files = downloadConcatAndUploadDocument();
        deleteProcessedFiles(files);
    }

    private List<File> downloadConcatAndUploadDocument() {
        List<File> files = new ArrayList<>();

        for (int i = 1; i <= MAX_ITERATION_COUNT; i++) {
            logger.info(Markers.append("context", new HashMap.SimpleEntry<>("iteration", i)), "Running processor application");
            try {
                files = downloadFiles();
                String bigDocument = joinFiles(files);
                String bigDocumentFilename = getDocumentFilename();
                String manifest = createManifest(bigDocument, bigDocumentFilename);
                ByteArrayOutputStream compressedDocument = compressFiles(bigDocument, bigDocumentFilename, manifest);
                uploadFiles(compressedDocument);

                return files;
            } catch (Exception e) {
                logger.error("Something went wrong", e);
            }
        }

        return files;
    }

    private void deleteProcessedFiles(List<File> files) {
        for (int i = 1; i <= MAX_ITERATION_COUNT; i++) {
            logger.info(Markers.append("context", new HashMap.SimpleEntry<>("iteration", i)), "Deleting processed files");
            try {
                documentStorage.deleteFiles(files);
                return;
            } catch (Exception e) {
                logger.error(Markers.append("context", new HashMap.SimpleEntry<>("files", files)), "Something went wrong while deleting files", e);
            }
        }
    }

    private String getDocumentFilename() {
        return manifestCreator.getDocumentFilename();
    }

    private String createManifest(String documentContent, String documentFilename) {
        return manifestCreator.createManifest(documentContent, documentFilename);
    }

    private void uploadFiles(ByteArrayOutputStream tarFile) {
        documentStorage.uploadFile(tarFile);
    }

    private ByteArrayOutputStream compressFiles(String documentString, String documentFileName, String manifest) throws IOException {
        return compressor.compressFiles(documentString, documentFileName, manifest);
    }

    private String joinFiles(List<File> files) {
        return documentJoiner.joinFiles(files);
    }

    private List<File> downloadFiles() {
        return documentStorage.downloadFiles();
    }
}
