package uk.gov.dvsa.moti.processing.executor;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManifestCreator {
    public static final String DATE_FORMAT = "yyyyMMddHHss";
    private static Logger logger = LoggerFactory.getLogger(ManifestCreator.class);
    private final String documentFilename;
    private ShaHasher shaHasher;

    @Inject
    public ManifestCreator(Date currentDate, ShaHasher shaHasher) {
        this.shaHasher = shaHasher;

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        documentFilename = String.format("documents-%s.xml", sdf.format(currentDate));
        logger.info("Exported xml is going to be named: " + documentFilename);
    }

    public String createManifest(String documentContent, String documentFilename) {
        String manifestContent = String.format("%s %s", shaHasher.sha256(documentContent), documentFilename);
        logger.info("Content of the manifest file:" + manifestContent);
        return manifestContent;
    }

    public String getDocumentFilename() {
        return documentFilename;
    }
}
