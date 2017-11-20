package com.test.domain.ioc.module;

import com.test.domain.connect.FeignClientBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 10/26/17.
 */
@Module(includes = ConfigurationModule.class)
public class ConnectorModule {

    @Provides
    @Singleton
    public FeignClientBuilder feignClientBuilder(){
        return new FeignClientBuilder();
    }



}
