package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.executor.DocumentStorage;

import java.io.ByteArrayOutputStream;

/**
 * Uploads documents
 */
public class DocumentUploadStep implements StepInterface {
    private DocumentStorage documentStorage;
    private byte[] documentToUpload;

    @Inject
    public DocumentUploadStep(DocumentStorage documentStorage) {
        this.documentStorage = documentStorage;
    }

    @Override
    public void runStep() {
        documentStorage.uploadFile(documentToUpload);
    }

    @Override
    public String getStepName() {
        return "Document upload";
    }

    public DocumentUploadStep setDocumentToUpload(byte[] documentToUpload) {
        this.documentToUpload = documentToUpload;
        return this;
    }
}
