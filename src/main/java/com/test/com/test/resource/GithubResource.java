package com.test.com.test.resource;

import com.codahale.metrics.annotation.Timed;
import com.netflix.config.ConfigurationManager;
import com.test.com.test.connect.GithubClient;
import com.test.com.test.entiry.GithubUser;
import com.test.configuration.HelloWorldConfiguration;
import com.test.configuration.modules.GithubApiConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by ryan on 9/14/17.
 */
@Path("/github")
@Produces(MediaType.APPLICATION_JSON)
public class GithubResource {

    private GithubApiConfig githubApiConfig;
    private Map<String, Object> hystrixConfig;

    public GithubResource(GithubApiConfig githubApiConfig, Map<String, Object> hystrixConfig) {
        this.githubApiConfig = githubApiConfig;
        this.hystrixConfig = hystrixConfig;
    }

    @GET
    @Timed
    @Path("/users/{username}")
    public GithubUser getUserProfile(@PathParam("username") final String username){

        //init hystrix config
        for (final Map.Entry<String, Object> config : hystrixConfig.entrySet()) {
            ConfigurationManager.getConfigInstance().setProperty(config.getKey(), config.getValue());
        }

        GithubClient client = new GithubClient(githubApiConfig);
        return client.getUserProfile(username).toBlocking().first();
    }

}
