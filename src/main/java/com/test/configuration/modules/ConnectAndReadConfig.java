package com.test.configuration.modules;

/**
 * Created by ryan-miao on 9/14/17.
 */
public class ConnectAndReadConfig {
    private int connectTimeoutMillis;
    private int readTimeoutMillis;

    public int getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public int getReadTimeoutMillis() {
        return readTimeoutMillis;
    }
}
