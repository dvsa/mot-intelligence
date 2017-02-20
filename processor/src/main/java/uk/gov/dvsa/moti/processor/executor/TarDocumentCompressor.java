package uk.gov.dvsa.moti.processor.executor;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import net.logstash.logback.marker.Markers;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.processor.configuration.model.ConfigurationName;
import uk.gov.dvsa.moti.processor.configuration.model.TarFileConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class TarDocumentCompressor implements DocumentCompressorInterface {
    private static Logger logger = LoggerFactory.getLogger(TarDocumentCompressor.class);
    private String manifestFileName;

    @Inject
    public TarDocumentCompressor(@Named(ConfigurationName.TAR_FILE) TarFileConfiguration tarFileConfiguration){
        this.manifestFileName = tarFileConfiguration.getManifestFileName();
    }

    public ByteArrayOutputStream compressFiles(String documentAsString, String documentFilename, String manifestContent) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            TarArchiveOutputStream tarOutput = new TarArchiveOutputStream(gzipOutputStream);
            addEntry(tarOutput, manifestFileName, manifestContent);
            addEntry(tarOutput, documentFilename, documentAsString);
            tarOutput.close();
            logger.info("Successfully created tar archive");
        } catch (IOException e) {
            logger.error("Error while creating tar archive", e);
        }

        return outputStream;
    }

    private void addEntry(TarArchiveOutputStream tarOutput, String fileName, String fileContent) {
        TarArchiveEntry xmlEntry = new TarArchiveEntry(fileName);
        xmlEntry.setSize(fileContent.getBytes().length);
        try {
            tarOutput.putArchiveEntry(xmlEntry);
            tarOutput.write(fileContent.getBytes());
            tarOutput.closeArchiveEntry();
            logger.info(Markers.append("context", new HashMap.SimpleEntry<>("fileName", fileName)), "Successfully added file to archive");
        } catch (IOException e) {
            logger.error("Error while adding file to tar archive", e);
        }
    }
}
