package uk.gov.dvsa.moti.processing;

import org.junit.Test;
import org.mockito.Mockito;

import uk.gov.dvsa.moti.processing.step.StepInterface;
import uk.gov.dvsa.moti.processing.step.StepRunner;

public class StepRunnerTest {
    @Test
    public void testRetrying(){
        StepRunner stepRunner = new StepRunner();
        StepInterface step = Mockito.mock(StepInterface.class);
        Mockito
                .doThrow(Exception.class)
                .doThrow(Exception.class)
                .doNothing()
                .when(step).runStep();

        stepRunner.runWithRetry(step, 2);
        Mockito.verify(step, Mockito.times(3)).runStep();
    }
}
