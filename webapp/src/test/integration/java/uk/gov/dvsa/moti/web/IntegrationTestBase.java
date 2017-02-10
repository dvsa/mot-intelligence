package uk.gov.dvsa.moti.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.dropwizard.jackson.Jackson;

import uk.gov.dvsa.moti.web.config.MotIntelligenceConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IntegrationTestBase {
    protected static MotIntelligenceConfiguration configuration;

    public static void setupClass() throws IOException {
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isFile()){
                String s = f.getName();
                int x = 5;
            }
        }

        ObjectMapper mapper = Jackson.newObjectMapper(new YAMLFactory());
        String configurationFilePath = System.getProperty("configurationFilePath");

        String configurationString = readFileToEnd(configurationFilePath);

        configuration = mapper.readValue(
                configurationString,
                MotIntelligenceConfiguration.class
        );
    }

    private static String readFileToEnd(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;

        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }
}
