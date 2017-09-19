package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.test.configuration.modules.GithubApiConfig;
import com.test.domain.connect.GithubClient;
import com.test.domain.entiry.GithubUser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Ryan Miao on 9/14/17.
 */
@Path("/github")
@Produces(MediaType.APPLICATION_JSON)
public class GithubResource {

    private GithubApiConfig githubApiConfig;

    public GithubResource(GithubApiConfig githubApiConfig) {
        this.githubApiConfig = githubApiConfig;
    }

    @GET
    @Timed
    @Path("/users/{username}")
    public GithubUser getUserProfile(@PathParam("username") final String username){
        GithubClient client = new GithubClient(githubApiConfig);
        return client.getUserProfile(username).toBlocking().first();
    }

}
