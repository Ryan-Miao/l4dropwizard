package com.test.domain.ioc.module;

import com.test.domain.service.IBingService;
import com.test.domain.service.IGithubService;
import com.test.domain.service.impl.BingService;
import com.test.domain.service.IMessageService;
import com.test.domain.service.impl.GithubService;
import com.test.domain.service.impl.MessageService;
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
    IGithubService githubService(GithubService service) {
        return service;
    }

    @Singleton
    @Provides
    public IBingService bingService(BingService service) {
        return service;
    }

    @Singleton
    @Provides
    IMessageService messageService(MessageService messageService){
        return messageService;
    }

}
