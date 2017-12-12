package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IBingService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("/bing")
@Path("/bing")
@Produces(MediaType.APPLICATION_JSON)
public class BingResource {
    private IBingService service;
    private final Logger LOGGER = LoggerFactory.getLogger(BingResource.class);

    @Inject
    public BingResource(IBingService service) {
        this.service = service;
    }

    @GET
    @Timed
    @Path("/images")
    @ApiOperation(value = "获取bing每日一图.", notes = "只做透传代理，不做任何逻辑变成。就是为了跨域。")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Valid credentials are required to access this resource."),
            @ApiResponse(code = 400, message = "Params not valid."),
            @ApiResponse(code = 500, message = "Something wrong from the server."),
            @ApiResponse(code = 200, message = "Success.", response = GithubUser.class)
    })
    public Response getUserProfile(
            @ApiParam("当前页") @DefaultValue("0") @QueryParam("idx") final int idx,
            @ApiParam("page size") @DefaultValue("1") @QueryParam("n") final int n,
            @HeaderParam("Origin") final String origin,
            @DefaultValue("86400")@HeaderParam("maxAge") final int maxAge
    ) {

        final CacheControl control = new CacheControl();
        control.setMaxAge(maxAge);
        control.setProxyRevalidate(true);
        control.setPrivate(false);

        LOGGER.error("Get origin:{}", origin);
        try {
            return Response.ok()
                    .entity(service.getImages(idx, n))
                    .encoding("UTF-8")
                    .allow("GET")
                    .cacheControl(control)
                    .header("Access-Control-Allow-Origin", origin)
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        } catch (Exception e) {
            return Response.serverError().encoding("UTF-8").entity(e.getMessage()).build();
        }

    }
}
