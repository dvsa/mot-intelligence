package uk.gov.dvsa.moti.processing.factory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import uk.gov.dvsa.moti.processing.configuration.model.ConfigurationName;
import uk.gov.dvsa.moti.processing.configuration.model.StorageConfiguration;
import uk.gov.dvsa.moti.processing.executor.DocumentStorage;

/**
 * Create DocumentStorage
 */
public class DocumentStorageFactory implements Provider<DocumentStorage> {
    private StorageConfiguration storageConfiguration;
    private S3StorageFactory s3StorageFactory;

    @Inject
    public DocumentStorageFactory(
            S3StorageFactory s3StorageFactory,
            @Named(ConfigurationName.STORAGE) StorageConfiguration storageConfiguration
    ) {
        this.s3StorageFactory = s3StorageFactory;
        this.storageConfiguration = storageConfiguration;
    }

    @Override
    public DocumentStorage get() {
        return new DocumentStorage(
                s3StorageFactory.getSourceBucketStorage(),
                s3StorageFactory.getDestinationBucketStorage(),
                storageConfiguration
        );
    }
}
