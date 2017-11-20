package com.test.domain.connect;

import com.test.configuration.modules.ConnectAndReadConfig;
import com.test.domain.exception.UpstreamException;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.io.IOUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ryan Miao on 9/21/17.
 */
public class FeignClientBuilder {

    private static final String REQUEST_ID = "requestId";
    private static final Map<String, Object> CONNECTORS = new ConcurrentHashMap<>();

    public <T> T getTargetWithString(Class<T> apiType, String commandKey, String baseUrl, ConnectAndReadConfig connectAndReadConfig) {
        return (T) CONNECTORS.computeIfAbsent(commandKey, k -> getTarget(apiType, baseUrl, connectAndReadConfig, new StringDecoder(), new GsonEncoder()));
    }

    public <T> T getTargetWithGson(Class<T> apiType, String commandKey, String baseUrl, ConnectAndReadConfig connectAndReadConfig) {
        return (T) CONNECTORS.computeIfAbsent(commandKey, k -> getTarget(apiType, baseUrl, connectAndReadConfig, new GsonDecoder(), new GsonEncoder()));
    }

    public <T> T getTargetWithJackson(Class<T> apiType, String commandKey, String baseUrl, ConnectAndReadConfig connectAndReadConfig) {
        return (T) CONNECTORS.computeIfAbsent(commandKey, k -> getTarget(apiType, baseUrl, connectAndReadConfig, new JacksonDecoder(), new JacksonEncoder()));
    }

    private static <T> T getTarget(Class<T> apiType, String baseUrl, ConnectAndReadConfig connectAndReadConfig, Decoder decoder, Encoder encoder) {
        return HystrixFeign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .errorDecoder((methodKey, response) -> {
                    final StringBuilder sb = new StringBuilder();
                    try {
                        sb.append("requestId=").append(response.request().headers().get(REQUEST_ID))
                                .append(";body=");
                        if (response.body() == null) {
                            sb.append("null");
                        } else {
                            sb.append(IOUtils.toString(response.body().asInputStream(), "UTF-8"));
                        }

                    } catch (Exception e) {
                        sb.append("Could not read body");
                    }
                    return new UpstreamException(response.status(), sb.toString());
                })
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .retryer(Retryer.NEVER_RETRY)
                .options(new Request.Options(connectAndReadConfig.getConnectTimeoutMillis(), connectAndReadConfig.getReadTimeoutMillis()))
                .requestInterceptor(requestTemplate ->
                        requestTemplate
                                .header(REQUEST_ID, UUID.randomUUID().toString())
                                .header("clientId", "l4dropwizard"))
                .target(apiType, baseUrl);
    }
}
