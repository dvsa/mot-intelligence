package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.executor.DocumentCompressorInterface;

import java.io.ByteArrayOutputStream;

/**
 * Compress documents
 */
public class DocumentCompressionStep implements StepInterface {
    private byte[] documentContent;
    private String documentFilename;
    private byte[] manifestContent;
    byte[] compressedDocument;

    private DocumentCompressorInterface documentCompressor;

    @Inject
    public DocumentCompressionStep(DocumentCompressorInterface documentCompressor) {
        this.documentCompressor = documentCompressor;
    }

    @Override
    public void runStep() {
        compressedDocument = documentCompressor.compressFiles(documentContent, documentFilename, manifestContent);
    }

    @Override
    public String getStepName() {
        return "Document compression";
    }

    public byte[] getCompressedDocument() {
        return compressedDocument;
    }

    public DocumentCompressionStep setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
        return this;
    }

    public DocumentCompressionStep setDocumentFilename(String documentFilename) {
        this.documentFilename = documentFilename;
        return this;
    }

    public DocumentCompressionStep setManifestContent(byte[] manifestContent) {
        this.manifestContent = manifestContent;
        return this;
    }
}
