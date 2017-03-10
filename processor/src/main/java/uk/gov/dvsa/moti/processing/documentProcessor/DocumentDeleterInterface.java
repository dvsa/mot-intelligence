package uk.gov.dvsa.moti.processing.documentProcessor;

import uk.gov.dvsa.moti.persistence.File;

import java.util.List;

public interface DocumentDeleterInterface {
    void deleteDocuments(List<File> documents);
}
