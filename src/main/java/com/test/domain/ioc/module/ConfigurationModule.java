package com.test.domain.ioc.module;

import com.test.configuration.HelloWorldConfiguration;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 11/20/17.
 */
@Module
public class ConfigurationModule {
    private final HelloWorldConfiguration configuration;

    public ConfigurationModule(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
    }


    @Provides
    @Singleton
    public HelloWorldConfiguration helloWorldConfiguration(){
        return configuration;
    }
}
