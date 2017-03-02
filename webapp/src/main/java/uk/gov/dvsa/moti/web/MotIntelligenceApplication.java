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

import uk.gov.dvsa.moti.web.bundle.FraudUrlViewHelperBundle;
import uk.gov.dvsa.moti.web.bundle.FormHelpersBundle;
import uk.gov.dvsa.moti.web.configuration.model.MotIntelligenceConfiguration;
import uk.gov.dvsa.moti.web.filter.CsrfTokenFilter;
import uk.gov.dvsa.moti.web.core.honeypot.HoneypotFilter;
import uk.gov.dvsa.moti.web.filter.RequestFilter;
import uk.gov.dvsa.moti.web.filter.SessionFilter;
import uk.gov.dvsa.moti.web.core.MotiErrorHandler;
import uk.gov.dvsa.moti.web.core.MotiExceptionMapper;
import uk.gov.dvsa.moti.web.factory.HttpSessionFactory;
import uk.gov.dvsa.moti.web.fraudSender.FraudSender;
import uk.gov.dvsa.moti.web.filter.VerifyCsrfTokenFilter;
import uk.gov.dvsa.moti.web.fraudSender.FraudSenderFactory;
import uk.gov.dvsa.moti.web.healthCheck.MotiHealthCheck;
import uk.gov.dvsa.moti.web.model.CsrfToken;
import uk.gov.dvsa.moti.web.resource.HomePageResource;
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
        bootstrap.addBundle(new AssetsBundle("/uk/gov/dvsa/moti/web/assets", "/assets/"));
        bootstrap.addBundle(new AssetsBundle("/uk/gov/dvsa/moti/web", "/robots.txt", "robots.txt", "robots"));
        bootstrap.addBundle(new FormHelpersBundle());
        bootstrap.addBundle(new FraudUrlViewHelperBundle());
    }

    @Override
    public void run(MotIntelligenceConfiguration configuration, Environment environment) {
        final MotFraudResource fraudResource = new MotFraudResource();
        final HomePageResource homePageResource = new HomePageResource();
        environment.jersey().register(fraudResource);
        environment.jersey().register(homePageResource);

        environment.jersey().register(RequestFilter.class);
        environment.jersey().register(SessionFactoryProvider.class);
        environment.jersey().register(FraudSenderFactory.class);
        environment.servlets().setSessionHandler(new SessionHandler());
        environment.servlets().addFilter("SessionFilter", new SessionFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        environment.servlets().addFilter("VerifyCsrfTokenFilter", new VerifyCsrfTokenFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        environment.servlets().addFilter("HoneypotFilter", new HoneypotFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(HttpSessionFactory.class).to(HttpSession.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);

                bind(configuration).to(MotIntelligenceConfiguration.class);

                bindFactory(FraudSenderFactory.class).to(FraudSender.class);

                bind(SessionResource.class).to(SessionResourceInterface.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);

                bind(FraudService.class).to(FraudService.class);

                bind(CsrfToken.class).to(CsrfToken.class).in(RequestScoped.class);
            }
        });

        environment.jersey().register(CsrfTokenFilter.class);
        environment.jersey().register(MotiExceptionMapper.class);
        environment.getApplicationContext().setErrorHandler(new MotiErrorHandler());

        MotiHealthCheck healthCheck = new MotiHealthCheck(configuration);
        environment.healthChecks().register("s3-check", healthCheck);
        environment.metrics().register("io.moti.s3.responseTime", healthCheck);
    }
}
