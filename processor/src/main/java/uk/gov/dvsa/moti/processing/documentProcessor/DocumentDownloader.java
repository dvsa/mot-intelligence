package uk.gov.dvsa.moti.processing.documentProcessor;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.step.DocumentsDownloadStep;
import uk.gov.dvsa.moti.processing.step.StepRunner;

import java.util.List;

/**
 * Download documents
 */
public class DocumentDownloader extends AbstractDocumentProcessor implements DocumentDownloaderInterface {
    private DocumentsDownloadStep documentsDownloadStep;

    @Inject
    public DocumentDownloader(
            DocumentsDownloadStep documentsDownloadStep,
            StepRunner stepRunner
    ) {
        super(stepRunner);
        this.documentsDownloadStep = documentsDownloadStep;
    }

    public List<File> downloadFiles() {
        runStep(documentsDownloadStep);
        return documentsDownloadStep.getFiles();
    }
}
