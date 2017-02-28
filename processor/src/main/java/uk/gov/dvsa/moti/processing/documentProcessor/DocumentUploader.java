package uk.gov.dvsa.moti.processing.documentProcessor;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.step.DocumentUploadStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.io.ByteArrayOutputStream;

public class DocumentUploader extends AbstractDocumentProcessor implements DocumentUploaderInterface {
    private DocumentUploadStep documentUploadStep;

    @Inject
    public DocumentUploader(DocumentUploadStep documentUploadStep, StepRunner stepRunner) {
        super(stepRunner);
        this.documentUploadStep = documentUploadStep;

    }

    public void uploadDocument(ByteArrayOutputStream document) {
        documentUploadStep.setDocumentToUpload(document);
        runStep(documentUploadStep);
    }
}
