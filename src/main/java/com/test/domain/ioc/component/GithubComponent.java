package com.test.domain.ioc.component;

import com.test.domain.ioc.module.ServiceModule;
import com.test.domain.resource.GithubResource;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Ryan Miao on 10/26/17.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface GithubComponent {
     GithubResource gitHubResource();
}
