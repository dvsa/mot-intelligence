package uk.gov.dvsa.moti.processing.documentProcessor;

import uk.gov.dvsa.moti.processing.step.StepInterface;
import uk.gov.dvsa.moti.processing.step.StepRunner;

public class AbstractDocumentProcessor {
    protected static final int RETRY_COUNT = 2;
    private StepRunner stepRunner;

    public AbstractDocumentProcessor(StepRunner stepRunner){
        this.stepRunner = stepRunner;
    }

    protected void runStep(StepInterface step) {
        stepRunner.runWithRetry(step, RETRY_COUNT);
    }
}
