package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.executor.DocumentStorage;

import java.util.List;

public class DocumentDeleteStep implements StepInterface {
    private DocumentStorage documentStorage;
    private List<File> documentsToDelete;

    @Inject
    public DocumentDeleteStep(DocumentStorage documentStorage) {
        this.documentStorage = documentStorage;
    }

    @Override
    public void runStep() {
        documentStorage.deleteFiles(documentsToDelete);
    }

    @Override
    public String getStepName() {
        return "Documents deletion";
    }

    public DocumentDeleteStep setDocumentsToDelete(List<File> documentsToDelete) {
        this.documentsToDelete = documentsToDelete;
        return this;
    }
}
