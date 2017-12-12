package com.test.domain.connect;

import com.test.configuration.HelloWorldConfiguration;
import com.test.configuration.modules.BingApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by Ryan Miao on 9/14/17.
 */
public class BingClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(BingClient.class);

    private FeignClientBuilder builder;
    private BingApiConfig bingApiConfig;

    @Inject
    public BingClient(FeignClientBuilder builder, HelloWorldConfiguration configuration) {
        this.builder = builder;
        this.bingApiConfig = configuration.getBingApiConfig();
    }

    public String getImgs(int size, int idx) {
        BingConnector connector = builder.getTargetWithString(BingConnector.class,
                FeignClientBuilder.CommandKey.getBingImgs, bingApiConfig.getBaseUrl(), bingApiConfig.getGetBingImgs());
        return connector.getBingImgs(idx, size);
    }
}
