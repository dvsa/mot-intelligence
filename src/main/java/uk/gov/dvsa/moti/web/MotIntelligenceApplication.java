package uk.gov.dvsa.moti.web;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.sessions.SessionFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import uk.gov.dvsa.moti.web.bundle.DisplayFormElementHelperBundle;
import uk.gov.dvsa.moti.web.filter.RequestFilter;
import uk.gov.dvsa.moti.web.filter.SessionFilter;
import uk.gov.dvsa.moti.web.core.MotiErrorHandler;
import uk.gov.dvsa.moti.web.core.MotiExceptionMapper;
import uk.gov.dvsa.moti.web.resource.MotFraudResource;
import uk.gov.dvsa.moti.web.resource.SessionResource;
import uk.gov.dvsa.moti.web.resource.SessionResourceInterface;
import uk.gov.dvsa.moti.web.service.FraudService;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpSession;

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
        bootstrap.addBundle(new AssetsBundle("/uk/gov/dvsa/moti/web", "/robots.txt", "/robots.txt", "robots"));
        bootstrap.addBundle(new DisplayFormElementHelperBundle());
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
        final MotFraudResource fraudResource = new MotFraudResource();
        environment.jersey().register(fraudResource);

        environment.jersey().register(RequestFilter.class);
        environment.jersey().register(SessionFactoryProvider.class);
        environment.servlets().setSessionHandler(new SessionHandler());
        environment.servlets().addFilter("SessionFilter", new SessionFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(uk.gov.dvsa.moti.web.factory.HttpSessionFactory.class).to(HttpSession.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);

                bind(SessionResource.class).to(SessionResourceInterface.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);

                bind(FraudService.class).to(FraudService.class);
            }
        });

        environment.jersey().register(MotiExceptionMapper.class);
        environment.getApplicationContext().setErrorHandler(new MotiErrorHandler());
    }
}
