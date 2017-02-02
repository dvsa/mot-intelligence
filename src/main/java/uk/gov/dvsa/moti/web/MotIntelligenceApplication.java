package uk.gov.dvsa.moti.web;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.sessions.SessionFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;
import uk.gov.dvsa.moti.web.core.MotiErrorHandler;
import uk.gov.dvsa.moti.web.core.MotiExceptionMapper;
import uk.gov.dvsa.moti.web.resource.MotFraudResource;

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
        bootstrap.addBundle(new AssetsBundle("/uk/gov/dvsa/moti/web/assets", "/assets"));
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
        final MotFraudResource fraudResource = new MotFraudResource();
        environment.jersey().register(fraudResource);

        environment.jersey().register(SessionFactoryProvider.class);
        environment.jersey().register(MotiExceptionMapper.class);
        environment.servlets().setSessionHandler(new SessionHandler());

        environment.getApplicationContext().setErrorHandler(new MotiErrorHandler());
    }
}