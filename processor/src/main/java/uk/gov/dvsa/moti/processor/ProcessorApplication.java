package uk.gov.dvsa.moti.processor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.gov.dvsa.moti.configuration.ConfigFileReader;
import uk.gov.dvsa.moti.processor.configuration.model.ProcessorConfiguration;
import uk.gov.dvsa.moti.processor.executor.Executor;
import uk.gov.dvsa.moti.processor.factory.ExecutorModule;

public class ProcessorApplication {
    public static void main(String[] args) {
        ProcessorConfiguration configuration = getConfiguration(args);
        Injector injector = Guice.createInjector(new ExecutorModule(configuration));
        Executor executor = injector.getInstance(Executor.class);
        executor.execute();
    }

    private static ProcessorConfiguration getConfiguration(String[] args) {
        ConfigFileReader reader = new ConfigFileReader();
        return reader.read(args[0], ProcessorConfiguration.class);
    }
}
