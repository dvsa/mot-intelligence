package uk.gov.dvsa.moti.processor;

import org.junit.Test;

import uk.gov.dvsa.moti.processor.executor.ShaHasher;


import static org.junit.Assert.assertEquals;

public class ShaHasherTest {

    @Test
    public void testShaCreation(){
        ShaHasher shaHasher = new ShaHasher();
        String sha = shaHasher.sha256("testString");
        assertEquals("4acf0b39d9c4766709a3689f553ac01ab550545ffa4544dfc0b2cea82fba02a3", sha);
    }
}
