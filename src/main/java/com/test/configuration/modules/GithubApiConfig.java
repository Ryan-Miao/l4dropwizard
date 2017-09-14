package com.test.configuration.modules;

/**
 * Created by ryan on 9/14/17.
 */
public class GithubApiConfig {
    private String baseUrl;
    private ConnectAndReadConfig getUserProfile;

    public String getBaseUrl() {
        return baseUrl;
    }

    public ConnectAndReadConfig getGetUserProfile() {
        return getUserProfile;
    }
}
