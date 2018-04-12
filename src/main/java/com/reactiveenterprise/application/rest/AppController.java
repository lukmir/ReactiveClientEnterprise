package com.reactiveenterprise.application.rest;

import com.reactiveenterprise.application.rest.client.UserResourceClient;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@Stateless
@Path("/controller")
public class AppController {

    @Inject
    private UserResourceClient userResourceClient;

    @GET
    public void generateNewUserAsynchronously(@Suspended AsyncResponse asyncResponse) {
        try {
            userResourceClient.getResponse()
                    .thenApply( r -> readResponse(r))
                    .thenAccept( res-> asyncResponse.resume(res));
        } catch (Exception ex) {
            asyncResponse.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build());
        }
    }

    private String readResponse(Response response) {
        return response.readEntity(String.class);
    }
}
