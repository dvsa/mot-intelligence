package uk.gov.dvsa.moti.processing.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.configuration.ConfigFileReader;
import uk.gov.dvsa.moti.configuration.ConfigurationException;
import uk.gov.dvsa.moti.processing.configuration.model.ProcessorConfiguration;

/**
 * Loads configuration from file and maps to specific configuration classes
 */
public class ConfigLoader {
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private static Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public ProcessorConfiguration getConfiguration(String[] args) {
        if (args.length == 1) {
            try {
                String filePath = args[0];
                logger.info("Loading config from " + filePath);
                return configFileReader.read(filePath, ProcessorConfiguration.class);
            } catch (ConfigurationException e) {
                logger.error("Could not load configuration", e);
                throw new ProcessorConfigurationException("Could not load configuration", e);
            }
        } else {
            logger.error("Wrong number of parameters. Path to config file is missing.");
            throw new ProcessorConfigurationException("Path to config file is missing. Pass it as the first argument");
        }
    }
}
