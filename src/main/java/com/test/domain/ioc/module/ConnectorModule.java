package com.test.domain.ioc.module;

import com.test.configuration.HelloWorldConfiguration;
import com.test.configuration.modules.GithubApiConfig;
import com.test.domain.connect.FeignClientBuilder;
import com.test.domain.connect.GithubClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 10/26/17.
 */
@Module
public class ConnectorModule {
    private HelloWorldConfiguration configuration;

    public ConnectorModule(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
    }

    @Provides
    @Singleton
    public FeignClientBuilder feignClientBuilder(){
        return new FeignClientBuilder();
    }

    @Provides
    @Singleton
    public GithubApiConfig githubApiConfig(){
        return configuration.getGithubApiConfig();
    }


}
