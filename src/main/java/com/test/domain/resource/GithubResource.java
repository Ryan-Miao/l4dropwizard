package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IGithubService;

import javax.inject.Inject;
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

    private IGithubService service;

    @Inject
    public GithubResource(IGithubService service) {
        this.service = service;
    }

    @GET
    @Timed
    @Path("/users/{username}")
    public GithubUser getUserProfile(@PathParam("username") final String username) {
        return service.getUserProfile(username);
    }

}
