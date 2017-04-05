package uk.gov.dvsa.moti.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ConfigFileReader {

    /**
     * Read configuration from given file
     * @param filePath path to file storing configuration
     * @param valueType class which configuration is being mapped to
     * @param <T>
     * @return
     */
    public <T> T read(String filePath, Class<T> valueType) {
        validateIfNotDistFile(filePath);

        ObjectMapper mapper = Jackson.newObjectMapper(new YAMLFactory());

        try {
            String configurationYml = readFileToEnd(filePath);

            return mapper.readValue(
                    configurationYml,
                    valueType
            );
        } catch (IOException ex) {
            throw new ConfigurationException("Could not read configuration file", ex);
        }
    }

    /**
     * Check if file is not .dist file
     * @param filePath
     */
    private void validateIfNotDistFile(String filePath) {
        if (filePath.endsWith(".dist")) {
            throw new ConfigurationException("Cannot load configuration from .dist file.");
        }
    }

    /**
     * Read file contents
     * @param filePath
     * @return
     * @throws IOException
     */
    private String readFileToEnd(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .collect(Collectors.joining("\n"));
    }
}
