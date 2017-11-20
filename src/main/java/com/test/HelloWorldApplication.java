package com.test;

import com.test.bundles.ConnectivityBundle;
import com.test.domain.health.TemplateHealthCheck;
import com.test.domain.ioc.component.DaggerGithubComponent;
import com.test.domain.ioc.component.GithubComponent;
import com.test.domain.ioc.module.ConfigurationModule;
import com.test.domain.resource.HelloWorldResource;
import com.test.configuration.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


/**
 * Created by Ryan Miao on 3/14/2017.
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
        bootstrap.addBundle(new ConnectivityBundle());
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

        registerResources(configuration, environment);

    }

    private void registerResources(HelloWorldConfiguration configuration, Environment environment) {
        GithubComponent component = DaggerGithubComponent.builder()
                .configurationModule(new ConfigurationModule(configuration))
                .build();
        environment.jersey().register(component.gitHubResource());
    }
}
