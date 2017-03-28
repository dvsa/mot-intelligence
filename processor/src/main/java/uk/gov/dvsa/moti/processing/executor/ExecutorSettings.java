package uk.gov.dvsa.moti.processing.executor;

/**
 * Executor settings
 */
public class ExecutorSettings {
    private boolean useCompression = false;

    public boolean useCompression() {
        return useCompression;
    }

    public ExecutorSettings setUseCompression(boolean useCompression) {
        this.useCompression = useCompression;
        return this;
    }
}
