package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.processing.executor.ManifestCreator;

public class ManifestCreatorStep implements StepInterface {
    private ManifestCreator manifestCreator;
    private String documentContent;
    private String documentFilename;
    private String manifest;

    @Inject
    public ManifestCreatorStep(ManifestCreator manifestCreator) {
        this.manifestCreator = manifestCreator;
    }

    @Override
    public void runStep() {
        manifest = manifestCreator.createManifest(documentContent, documentFilename);
    }

    @Override
    public String getStepName() {
        return "Manifest creator";
    }

    public ManifestCreatorStep setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
        return this;
    }

    public ManifestCreatorStep setDocumentFilename(String documentFilename) {
        this.documentFilename = documentFilename;
        return this;
    }

    public String getManifest() {
        return manifest;
    }
}
