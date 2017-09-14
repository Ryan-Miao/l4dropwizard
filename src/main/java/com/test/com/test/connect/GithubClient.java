package com.test.com.test.connect;

import com.netflix.config.ConfigurationManager;
import com.test.com.test.entiry.GithubUser;
import com.test.configuration.modules.ConnectAndReadConfig;
import com.test.configuration.modules.GithubApiConfig;
import feign.Request;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * Created by ryan on 9/14/17.
 */
public class GithubClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    private GithubApiConfig githubApiConfig;

    public GithubClient(GithubApiConfig githubApiConfig) {
        this.githubApiConfig = githubApiConfig;
    }


    public Observable<GithubUser> getUserProfile(String username){
        String baseUrl = githubApiConfig.getBaseUrl();
        ConnectAndReadConfig getUserProfile = githubApiConfig.getGetUserProfile();
        GithubConnector connector = HystrixFeign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .options(new Request.Options(getUserProfile.getConnectTimeoutMillis(), getUserProfile.getReadTimeoutMillis()))
                .target(GithubConnector.class, baseUrl);

        return connector.getUserProfile(username).onErrorReturn(error -> {
            LOGGER.error("Get github user profile failed. ", error);
            return null;
        });
    }
}
