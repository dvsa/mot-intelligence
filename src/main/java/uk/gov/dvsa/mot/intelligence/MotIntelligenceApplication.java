package uk.gov.dvsa.mot.intelligence;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import uk.gov.dvsa.mot.intelligence.resource.MotFraudResource;

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
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/uk/gov/dvsa/mot/intelligence/assets", "/assets"));
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
        final MotFraudResource fraudResource = new MotFraudResource();
        environment.jersey().register(fraudResource);
    }
}
