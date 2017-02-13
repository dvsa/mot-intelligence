package uk.gov.dvsa.moti.web;

import uk.gov.dvsa.moti.configuration.ConfigFileReader;
import uk.gov.dvsa.moti.web.configuration.model.MotIntelligenceConfiguration;

import java.io.IOException;

public class IntegrationTestBase {
    protected static MotIntelligenceConfiguration configuration;

    public static void setupClass() throws IOException {
        String configurationFilePath = System.getProperty("configurationFilePath");

        ConfigFileReader reader = new ConfigFileReader();
        configuration = reader.read(configurationFilePath, MotIntelligenceConfiguration.class);
    }
}
