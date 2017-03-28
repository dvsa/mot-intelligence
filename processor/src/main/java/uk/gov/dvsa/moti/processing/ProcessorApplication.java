package uk.gov.dvsa.moti.processing;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.processing.configuration.ConfigLoader;
import uk.gov.dvsa.moti.processing.configuration.ProcessorConfigurationException;
import uk.gov.dvsa.moti.processing.configuration.model.ProcessorConfiguration;
import uk.gov.dvsa.moti.processing.executor.ProcessorException;
import uk.gov.dvsa.moti.processing.factory.ExecutorModule;

/**
 * Processor application
 */
public class ProcessorApplication {
    private static Logger logger = LoggerFactory.getLogger(ProcessorApplication.class);

    public static void main(String[] args) {
        try {
            ProcessorConfiguration configuration = getConfiguration(args);
            Injector injector = Guice.createInjector(new ExecutorModule(configuration));
            Processor processor = injector.getInstance(Processor.class);
            processor.execute();
        } catch (ProcessorConfigurationException e) {
            logger.error("Error loading config file", e);
            System.exit(1);
        } catch (ProcessorException e) {
            logger.error("Something went wrong while processing files", e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            System.exit(1);
        }
    }

    private static ProcessorConfiguration getConfiguration(String[] args) {
        ConfigLoader reader = new ConfigLoader();
        return reader.getConfiguration(args);
    }
}
