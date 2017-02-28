package uk.gov.dvsa.moti.processing.step;

import net.logstash.logback.marker.Markers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.dvsa.moti.processing.executor.ProcessorException;

import java.util.HashMap;

public class StepRunner {
    private static Logger logger = LoggerFactory.getLogger(StepRunner.class);

    public void runWithRetry(StepInterface step, int retryCount) {
        for (int i = 0; i <= retryCount; i++) {
            logger.info(Markers.append("context", new HashMap.SimpleEntry<>("iteration", i)), step.getStepName() + ": iteration " + i);
            try {
                step.runStep();
                return;
            } catch (Exception e) {
                logger.error(Markers.append("context", new HashMap.SimpleEntry<>("step", step.getStepName())), "Failed to run step", e);
            }
        }

        throw new ProcessorException("Failed to run step: " + step.getStepName() + ". Tried " + retryCount + " times");
    }
}
