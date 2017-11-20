package com.test.bundles;

import com.netflix.config.ConfigurationManager;
import com.test.configuration.HelloWorldConfiguration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

/**
 * Created by Ryan Miao on 10/26/17.
 */
public class ConnectivityBundle implements ConfiguredBundle<HelloWorldConfiguration> {
    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        //init hystrix config
        Map<String, Object> hystrixConfig = configuration.getHystrixConfig();
        for (final Map.Entry<String, Object> config : hystrixConfig.entrySet()) {
            ConfigurationManager.getConfigInstance().setProperty(config.getKey(), config.getValue());
        }
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }
}
