package com.test.domain.ioc.module;

import com.miao.easyi18n.support.ResourceBundleMessageSource;
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
    HelloWorldConfiguration helloWorldConfiguration(){
        return configuration;
    }

    @Singleton
    @Provides
    ResourceBundleMessageSource resourceBundleMessageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("i18n/messages", "i18n/messages2", "i18n/otherGroup");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
