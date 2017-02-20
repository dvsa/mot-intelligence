package uk.gov.dvsa.moti.processor.factory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import uk.gov.dvsa.moti.processor.configuration.model.ConfigurationName;
import uk.gov.dvsa.moti.processor.configuration.model.ProcessorConfiguration;
import uk.gov.dvsa.moti.processor.configuration.model.S3Configuration;
import uk.gov.dvsa.moti.processor.configuration.model.StorageConfiguration;
import uk.gov.dvsa.moti.processor.configuration.model.TarFileConfiguration;
import uk.gov.dvsa.moti.processor.executor.TarDocumentCompressor;
import uk.gov.dvsa.moti.processor.executor.DocumentCompressorInterface;
import uk.gov.dvsa.moti.processor.executor.DocumentStorage;

public class ExecutorModule extends AbstractModule {

    private ProcessorConfiguration configuration;

    public ExecutorModule(ProcessorConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(ProcessorConfiguration.class)
                .annotatedWith(Names.named(ConfigurationName.PROCESSOR))
                .toInstance(configuration);
        bind(StorageConfiguration.class)
                .annotatedWith(Names.named(ConfigurationName.STORAGE))
                .toInstance(configuration.getStorageConfiguration());
        bind(S3Configuration.class)
                .annotatedWith(Names.named(ConfigurationName.S3))
                .toInstance(configuration.getS3Configuration());
        bind(TarFileConfiguration.class)
                .annotatedWith(Names.named(ConfigurationName.TAR_FILE))
                .toInstance(configuration.getTarFileConfiguration());
        bind(DocumentStorage.class).toProvider(DocumentStorageFactory.class);
        bind(DocumentCompressorInterface.class).to(TarDocumentCompressor.class);
    }
}