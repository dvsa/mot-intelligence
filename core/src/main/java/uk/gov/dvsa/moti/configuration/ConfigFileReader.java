package uk.gov.dvsa.moti.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ConfigFileReader {

    public <T> T read(String filePath, Class<T> valueType) {
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

    private String readFileToEnd(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .collect(Collectors.joining("\n"));
    }
}
