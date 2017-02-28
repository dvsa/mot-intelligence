package uk.gov.dvsa.moti.processing.documentProcessor;

import uk.gov.dvsa.moti.persistence.File;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface DocumentProcessorInterface {
    ByteArrayOutputStream processFiles(List<File> documentsList);
}
