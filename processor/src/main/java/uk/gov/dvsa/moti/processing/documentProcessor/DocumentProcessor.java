package uk.gov.dvsa.moti.processing.documentProcessor;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.step.DocumentCompressionStep;
import uk.gov.dvsa.moti.processing.step.DocumentFilenameStep;
import uk.gov.dvsa.moti.processing.step.DocumentJoinStep;
import uk.gov.dvsa.moti.processing.step.ManifestCreatorStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DocumentProcessor extends AbstractDocumentProcessor implements DocumentProcessorInterface {
    private final DocumentJoinStep documentJoinStep;
    private final DocumentFilenameStep documentFilenameStep;
    private final ManifestCreatorStep manifestCreatorStep;
    private final DocumentCompressionStep documentCompressionStep;

    @Inject
    public DocumentProcessor(
            DocumentJoinStep documentJoinStep,
            DocumentFilenameStep documentFilenameStep,
            ManifestCreatorStep manifestCreatorStep,
            DocumentCompressionStep documentCompressionStep,
            StepRunner stepRunner
    ) {
        super(stepRunner);
        this.documentJoinStep = documentJoinStep;
        this.documentFilenameStep = documentFilenameStep;
        this.manifestCreatorStep = manifestCreatorStep;
        this.documentCompressionStep = documentCompressionStep;
    }

    public ByteArrayOutputStream processFiles(List<File> documentsList) {
        String bigDocument = joinFiles(documentsList);
        String bigDocumentFilename = getDocumentFilename();
        String manifestContent = createManifestFromDocument(bigDocument, bigDocumentFilename);

        return compressDocument(bigDocument, bigDocumentFilename, manifestContent);
    }

    private ByteArrayOutputStream compressDocument(String bigDocument, String bigDocumentFilename, String manifestContent) {
        documentCompressionStep.setDocumentContent(bigDocument);
        documentCompressionStep.setDocumentFilename(bigDocumentFilename);
        documentCompressionStep.setManifestContent(manifestContent);
        runStep(documentCompressionStep);

        return documentCompressionStep.getCompressedDocument();
    }

    private String createManifestFromDocument(String bigDocument, String bigDocumentFilename) {
        manifestCreatorStep.setDocumentFilename(bigDocumentFilename);
        manifestCreatorStep.setDocumentContent(bigDocument);
        runStep(manifestCreatorStep);

        return manifestCreatorStep.getManifest();
    }

    private String getDocumentFilename() {
        runStep(documentFilenameStep);

        return documentFilenameStep.getDocumentFilename();
    }

    private String joinFiles(List<File> documentsList) {
        documentJoinStep.setFiles(documentsList);
        runStep(documentJoinStep);

        return documentJoinStep.getJoinedFile();
    }
}
