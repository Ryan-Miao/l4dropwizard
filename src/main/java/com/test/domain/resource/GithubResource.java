package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IGithubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Locale;

/**
 * Created by Ryan Miao on 9/14/17.
 */
@Api("/github")
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
    @ApiOperation(value = "Get github user profile.", notes = "There should be the note.")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Valid credentials are required to access this resource."),
            @ApiResponse(code = 400, message = "Params not valid."),
            @ApiResponse(code = 500, message = "Something wrong from the server."),
            @ApiResponse(code = 200, message = "Success.", response = GithubUser.class)
    })
    public GithubUser getUserProfile(@PathParam("username") final String username) {
        return service.getUserProfile(username);
    }

}
