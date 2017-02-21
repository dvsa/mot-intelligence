package uk.gov.dvsa.moti.processing;

import uk.gov.dvsa.moti.configuration.ConfigFileReader;
import uk.gov.dvsa.moti.processing.configuration.model.ProcessorConfiguration;

import java.io.IOException;

public class IntegrationTestBase {
    protected static ProcessorConfiguration configuration;

    public static void setupClass() throws IOException {
        String configurationFilePath = System.getProperty("configurationFilePath");

        ConfigFileReader reader = new ConfigFileReader();
        configuration = reader.read(configurationFilePath, ProcessorConfiguration.class);
    }
}
