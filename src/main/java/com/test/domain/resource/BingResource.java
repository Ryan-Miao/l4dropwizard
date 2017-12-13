package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IBingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER;

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
    public Response getImages(
            @Context HttpServletRequest request,
            @ApiParam("当前页") @DefaultValue("0") @QueryParam("idx") final int idx,
            @ApiParam("page size") @DefaultValue("1") @QueryParam("n") final int n,
            @HeaderParam("Origin") final String origin,
            @DefaultValue("86400") @HeaderParam("maxAge") final int maxAge
    ) throws URISyntaxException {

        final String referer = request.getHeader("Referer");
        final String pattern = "(https?://www\\.cnblogs\\.com/woshimrf[^\\s]*)|(https?://api.rmiao.top[^\\s]*)|(http://localhost:9008/swagger)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(referer);
        if (!m.find()) {
            return Response.seeOther(new URI("http://www.cnblogs.com/woshimrf/")).build();
        }

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        LOGGER.error("Headers: {}", headers);

        final CacheControl control = new CacheControl();
        control.setMaxAge(maxAge);

        try {
            return Response.ok()
                    .entity(service.getImages(idx, n))
                    .encoding("UTF-8")
                    .allow("GET")
                    .cacheControl(control)
                    .header(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, origin)
                    .header(ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true")
                    .build();
        } catch (Exception e) {
            return Response.serverError().encoding("UTF-8").entity(e.getMessage()).build();
        }

    }
}
