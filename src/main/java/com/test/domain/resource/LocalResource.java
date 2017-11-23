package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import com.test.domain.entiry.GithubUser;
import com.test.domain.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Locale;
import java.util.Map;

/**
 * Test localization
 * Created by Ryan Miao on 11/23/17.
 */
@Api("/local")
@Path("/local")
@Produces(MediaType.APPLICATION_JSON)
public class LocalResource {

    private final IMessageService messageService;

    @Inject
    public LocalResource(IMessageService messageService) {
        this.messageService = messageService;
    }

    @GET
    @Timed
    @Path("/{key}")
    @ApiOperation(value = "Get github user profile.", notes = "There should be the note.")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Valid credentials are required to access this resource."),
            @ApiResponse(code = 400, message = "Params not valid."),
            @ApiResponse(code = 500, message = "Something wrong from the server."),
            @ApiResponse(code = 200, message = "Success.", response = GithubUser.class)
    })
    public Map<String, String> getIndex(
            @PathParam("key") final String index,
            @HeaderParam("Accept-Language") @Valid
            @NotNull(message = "cannot be null.")
            @Pattern(regexp = "([a-z]{2}-[A-Z]{2})", message = "pattern should like zh-CN, en-US.")
            final String language
    ) {
        final Locale locale = Locale.forLanguageTag(language);
        final String message = messageService.getMessage(index, locale);
        return ImmutableMap.of(index, message);
    }
}
