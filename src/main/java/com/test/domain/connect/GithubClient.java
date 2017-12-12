package com.test.domain.connect;

import com.test.configuration.HelloWorldConfiguration;
import com.test.configuration.modules.GithubApiConfig;
import com.test.domain.entiry.GithubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import javax.inject.Inject;

/**
 * Created by Ryan Miao on 9/14/17.
 */
public class GithubClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    private FeignClientBuilder builder;
    private GithubApiConfig githubApiConfig;

    @Inject
    public GithubClient(FeignClientBuilder builder, HelloWorldConfiguration configuration) {
        this.builder = builder;
        this.githubApiConfig = configuration.getGithubApiConfig();
    }

    public Observable<GithubUser> getUserProfile(String username) {
        GithubConnector connector = builder.getTargetWithJackson(GithubConnector.class,
                FeignClientBuilder.CommandKey.getUserProfile, githubApiConfig.getBaseUrl(), githubApiConfig.getGetUserProfile());
        return connector.getUserProfile(username);
    }
}
