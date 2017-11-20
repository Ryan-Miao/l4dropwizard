package com.test.bundles;

import com.test.configuration.HelloWorldConfiguration;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SwitchableSwaggerBundle extends SwaggerBundle<HelloWorldConfiguration> {

    @Override
    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorldConfiguration configuration) {
        return configuration.getSwaggerBundleConfiguration();
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}
