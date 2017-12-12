package com.test.domain.ioc.component;

import com.test.domain.ioc.module.ServiceModule;
import com.test.domain.resource.BingResource;
import com.test.domain.resource.GithubResource;
import com.test.domain.resource.LocalResource;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 10/26/17.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface GithubComponent {
     GithubResource gitHubResource();
     BingResource bing();
     LocalResource localResource();
}
