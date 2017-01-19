package uk.gov.dvsa.mot.intelligence;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import uk.gov.dvsa.mot.intelligence.controllers.MotFraudController;

public class MotIntelligenceApplication extends Application<MotIntelligenceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MotIntelligenceApplication().run(args);
    }

    @Override
    public String getName() {
        return "mot-intelligence";
    }

    @Override
    public void initialize(Bootstrap<MotIntelligenceConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<MotIntelligenceConfiguration>());
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
        final MotFraudController fraudController = new MotFraudController(
        );
        environment.jersey().register(fraudController);
    }
}
