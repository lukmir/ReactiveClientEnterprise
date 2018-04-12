package com.reactiveenterprise.application.rest;

import com.reactiveenterprise.application.model.User;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Stateless
@Path("/userResource")
public class UserResource {

    @GET
    public Response createNewUser() {
        try {
            TimeUnit.SECONDS.sleep(5);
            int age = new Random(10).nextInt();
            return Response.ok(new User("User: " + age, age)).build();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
}
