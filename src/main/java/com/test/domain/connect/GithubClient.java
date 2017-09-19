package com.test.domain.connect;

import com.test.configuration.modules.ConnectAndReadConfig;
import com.test.configuration.modules.GithubApiConfig;
import com.test.domain.entiry.GithubUser;
import feign.Request;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Ryan Miao on 9/14/17.
 */
public class GithubClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    private GithubApiConfig githubApiConfig;

    public GithubClient(GithubApiConfig githubApiConfig) {
        this.githubApiConfig = githubApiConfig;
    }


    public Observable<GithubUser> getUserProfile(String username) {
        String baseUrl = githubApiConfig.getBaseUrl();
        ConnectAndReadConfig getUserProfile = githubApiConfig.getGetUserProfile();
        GithubConnector connector = HystrixFeign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .logger(new Slf4jLogger())
                .options(new Request.Options(getUserProfile.getConnectTimeoutMillis(), getUserProfile.getReadTimeoutMillis()))
                .errorDecoder((methodKey, response) -> {
                    StringBuilder msg = new StringBuilder("status=").append(response.status())
                            .append(";request_headers=").append(response.request().headers())
                            .append(";response_headers=").append(response.headers())
                            .append(";body=");
                    Response.Body body = response.body();
                    if (body != null && body.length() > 0) {
                        try {
                            msg.append(IOUtils.toString(body.asReader()));
                        } catch (IOException e) {
                            msg.append("can not read body,"+e.getMessage());
                        }
                    }

                    return new RuntimeException(msg.toString());
                })
                .requestInterceptor(template -> template.header("requestId", UUID.randomUUID().toString()))
                .target(GithubConnector.class, baseUrl);

        return connector.getUserProfile(username).onErrorReturn(error -> {
            LOGGER.error("Get github user profile failed. ", error);
            return null;
        });
    }
}
