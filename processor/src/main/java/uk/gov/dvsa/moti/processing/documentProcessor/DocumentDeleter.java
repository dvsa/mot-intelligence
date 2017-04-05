package uk.gov.dvsa.moti.processing.documentProcessor;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.step.DocumentDeleteStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.util.List;

/**
 * Delete documents
 */
public class DocumentDeleter extends AbstractDocumentProcessor implements DocumentDeleterInterface {
    private DocumentDeleteStep documentDeleteStep;

    @Inject
    public DocumentDeleter(
            DocumentDeleteStep documentDeleteStep,
            StepRunner stepRunner) {
        super(stepRunner);
        this.documentDeleteStep = documentDeleteStep;
    }

    public void deleteDocuments(List<File> documents) {
        documentDeleteStep.setDocumentsToDelete(documents);
        runStep(documentDeleteStep);
    }
}
