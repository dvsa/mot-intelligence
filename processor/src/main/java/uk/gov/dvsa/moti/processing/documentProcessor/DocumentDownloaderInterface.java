package uk.gov.dvsa.moti.processing.documentProcessor;

import uk.gov.dvsa.moti.persistence.File;

import java.util.List;

public interface DocumentDownloaderInterface {
    List<File> downloadFiles();
}
