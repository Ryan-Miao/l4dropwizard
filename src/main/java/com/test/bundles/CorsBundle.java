package com.test.bundles;

import com.google.common.collect.ImmutableMap;
import com.test.configuration.HelloWorldConfiguration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class CorsBundle implements ConfiguredBundle<HelloWorldConfiguration> {

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {

        final FilterRegistration.Dynamic filter = environment.servlets().addFilter(
                CrossOriginFilter.class.getSimpleName(), CrossOriginFilter.class);

        filter.setInitParameters(ImmutableMap.of(
                CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:8080,http://localhost:9008, http://cnblogs",
                CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true",
                CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*",
                CrossOriginFilter.ALLOWED_METHODS_PARAM, "*",
                CrossOriginFilter.EXPOSED_HEADERS_PARAM, "*"
        ));
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "*", "/swagger.json");

    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }
}
