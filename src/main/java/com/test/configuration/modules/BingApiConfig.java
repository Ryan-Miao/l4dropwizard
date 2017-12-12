package com.test.configuration.modules;

/**
 * Created by ryan on 9/14/17.
 */
public class BingApiConfig {
    private String baseUrl;
    private ConnectAndReadConfig getBingImgs;

    public String getBaseUrl() {
        return baseUrl;
    }

    public ConnectAndReadConfig getGetBingImgs() {
        return getBingImgs;
    }
}
