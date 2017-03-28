package uk.gov.dvsa.moti.processing.step;

import com.google.inject.Inject;

import uk.gov.dvsa.moti.persistence.File;
import uk.gov.dvsa.moti.processing.executor.DocumentJoiner;

import java.util.List;

/**
 * Join documents
 */
public class DocumentJoinStep implements StepInterface {
    private DocumentJoiner documentJoiner;
    private List<File> files;
    private String joinedFile;

    @Inject
    public DocumentJoinStep(DocumentJoiner documentJoiner) {
        this.documentJoiner = documentJoiner;
    }

    @Override
    public void runStep() {
        joinedFile = documentJoiner.joinFiles(files);
    }

    @Override
    public String getStepName() {
        return "Document joining";
    }

    public DocumentJoinStep setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public String getJoinedFile() {
        return joinedFile;
    }
}
