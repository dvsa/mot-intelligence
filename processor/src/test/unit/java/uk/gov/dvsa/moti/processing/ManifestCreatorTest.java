package uk.gov.dvsa.moti.processing;

import org.junit.Test;

import uk.gov.dvsa.moti.processing.executor.ManifestCreator;
import uk.gov.dvsa.moti.processing.executor.ShaHasher;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ManifestCreatorTest {

    @Test
    public void testManifestCreation(){
        Date date = new Date();
        ManifestCreator manifestCreator = new ManifestCreator(date, new ShaHasher());
        String manifest = manifestCreator.createManifest("testString", "filename");

        assertEquals("4acf0b39d9c4766709a3689f553ac01ab550545ffa4544dfc0b2cea82fba02a3 filename", manifest);
    }

    @Test
    public void testXmlFilenameCreation(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHss");
        ManifestCreator manifestCreator = new ManifestCreator(date, new ShaHasher());
        String xmlFilename = manifestCreator.getDocumentFilename();
        String expectedFilename = "documents-" + sdf.format(date) + ".xml";

        assertEquals(expectedFilename, xmlFilename);
    }
}
