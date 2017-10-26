package com.test.domain.ioc.module;

import com.test.domain.service.IGithubService;
import com.test.domain.service.impl.GithubService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 10/26/17.
 */
@Module(includes = ConnectorModule.class)
public class ServiceModule {

    @Singleton
    @Provides
    public IGithubService githubService(GithubService service) {
        return service;
    }
}
