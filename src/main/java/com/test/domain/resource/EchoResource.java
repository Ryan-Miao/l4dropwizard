package com.test.domain.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/")
@Api("/echo")
public class EchoResource {
    @GET
    @Path("/echo/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Echo back your name")
    @Timed
    public Response echo(@PathParam("name") final String name) {
        final JsonObject jsonElement = new JsonObject();
        jsonElement.addProperty("name", name);
        return Response.status(200).type(MediaType.APPLICATION_JSON).entity(new Gson().toJson(jsonElement)).build();
    }

    @GET
    @Path("/")
    public Object getView() {
        final URI uri = UriBuilder.fromUri("/swagger").build();
        return Response.seeOther(uri).build();
    }
}
