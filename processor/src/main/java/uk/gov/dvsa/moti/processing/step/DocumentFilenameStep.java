package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.executor.ManifestCreator;

/**
 * Create docuemnt file name
 */
public class DocumentFilenameStep implements StepInterface {
    private ManifestCreator manifestCreator;
    private String documentFilename;

    @Inject
    public DocumentFilenameStep(ManifestCreator manifestCreator) {
        this.manifestCreator = manifestCreator;
    }

    @Override
    public void runStep() {
        documentFilename = manifestCreator.getDocumentFilename();
    }

    @Override
    public String getStepName() {
        return "Document filename generation";
    }

    public String getDocumentFilename() {
        return documentFilename;
    }
}
