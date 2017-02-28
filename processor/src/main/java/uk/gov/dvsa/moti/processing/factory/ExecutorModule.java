package uk.gov.dvsa.moti.processing.factory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import uk.gov.dvsa.moti.processing.configuration.model.ConfigurationName;
import uk.gov.dvsa.moti.processing.configuration.model.ProcessorConfiguration;
import uk.gov.dvsa.moti.processing.configuration.model.S3Configuration;
import uk.gov.dvsa.moti.processing.configuration.model.StorageConfiguration;
import uk.gov.dvsa.moti.processing.configuration.model.TarFileConfiguration;
import uk.gov.dvsa.moti.processing.executor.DocumentCompressorInterface;
import uk.gov.dvsa.moti.processing.executor.DocumentStorage;
import uk.gov.dvsa.moti.processing.executor.TarDocumentCompressor;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDeleter;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDeleterInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDownloader;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentDownloaderInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentProcessor;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentProcessorInterface;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentUploader;
import uk.gov.dvsa.moti.processing.documentProcessor.DocumentUploaderInterface;

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

        bind(DocumentDownloaderInterface.class).to(DocumentDownloader.class);
        bind(DocumentProcessorInterface.class).to(DocumentProcessor.class);
        bind(DocumentUploaderInterface.class).to(DocumentUploader.class);
        bind(DocumentDeleterInterface.class).to(DocumentDeleter.class);
    }
}