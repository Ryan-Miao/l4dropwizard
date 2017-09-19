package com.test;

import com.netflix.config.ConfigurationManager;
import com.test.domain.health.TemplateHealthCheck;
import com.test.domain.resource.GithubResource;
import com.test.domain.resource.HelloWorldResource;
import com.test.configuration.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

/**
 * Created by rmiao on 3/14/2017.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(healthCheck);

        //init hystrix config
        Map<String, Object> hystrixConfig = configuration.getHystrixConfig();
        for (final Map.Entry<String, Object> config : hystrixConfig.entrySet()) {
            ConfigurationManager.getConfigInstance().setProperty(config.getKey(), config.getValue());
        }

        environment.jersey().register(new GithubResource(configuration.getGithubApiConfig()));

    }
}
