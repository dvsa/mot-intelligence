package uk.gov.dvsa.moti.processing.documentProcessor;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.step.DocumentUploadStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.io.ByteArrayOutputStream;

/**
 * Uploads document
 */
public class DocumentUploader extends AbstractDocumentProcessor implements DocumentUploaderInterface {
    private DocumentUploadStep documentUploadStep;

    @Inject
    public DocumentUploader(DocumentUploadStep documentUploadStep, StepRunner stepRunner) {
        super(stepRunner);
        this.documentUploadStep = documentUploadStep;

    }

    /**
     * Uploads document
     * @param documentContent
     */
    public void uploadDocument(byte[] documentContent) {
        documentUploadStep.setDocumentToUpload(documentContent);
        runStep(documentUploadStep);
    }
}
