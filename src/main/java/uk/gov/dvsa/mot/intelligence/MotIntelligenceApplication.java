package uk.gov.dvsa.mot.intelligence;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
<<<<<<< HEAD
import uk.gov.dvsa.mot.intelligence.controllers.MotFraudController;
=======
import uk.gov.dvsa.mot.intelligence.resource.MotFraudResource;
>>>>>>> b31e0e7... BL-4492 replaced free marker with handlebars

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
<<<<<<< HEAD
        bootstrap.addBundle(new ViewBundle<MotIntelligenceConfiguration>());
=======
        bootstrap.addBundle(new ViewBundle<>());
>>>>>>> b31e0e7... BL-4492 replaced free marker with handlebars
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
<<<<<<< HEAD
        final MotFraudController fraudController = new MotFraudController(
        );
        environment.jersey().register(fraudController);
=======
        final MotFraudResource fraudResource = new MotFraudResource(
        );
        environment.jersey().register(fraudResource);
>>>>>>> b31e0e7... BL-4492 replaced free marker with handlebars
    }
}
