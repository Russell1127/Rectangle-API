package com.nuvalence;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.nuvalence.controllers.HealthCheckController;
import com.nuvalence.controllers.RectanglesController;
import com.nuvalence.health.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;

public class RectanglesApplication extends Application<RectanglesConfiguration> {

    public static void main(String[] args) throws Exception {
        new RectanglesApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RectanglesConfiguration> bootstrap) {
        bootstrap.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public void run(RectanglesConfiguration rectanglesConfiguration, Environment environment) throws Exception {
        final Client client = new JerseyClientBuilder().build();

        environment.jersey().register(new RectanglesController());

        //Application health check
        environment.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));

        //Run multiple health checks
        environment.jersey().register(new HealthCheckController(environment.healthChecks()));

        // CORS Configuration
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:4200"); // Will need to change for testing UI on another port etc.
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
