package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.executor.DocumentStorage;

import java.util.List;

/**
 * Downloads documents
 */
public class DocumentsDownloadStep implements StepInterface {
    private DocumentStorage documentStorage;
    private List<File> files;

    @Inject
    public DocumentsDownloadStep(DocumentStorage documentStorage) {
        this.documentStorage = documentStorage;
    }

    @Override
    public void runStep() {
        files = documentStorage.downloadFiles();
    }

    @Override
    public String getStepName() {
        return "Document downloading";
    }

    public List<File> getFiles() {
        return files;
    }
}
