package com.reactiveenterprise.application.rest.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Stateless
public class UserResourceClient {

    private Client client;
    private WebTarget webTarget;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        webTarget = client.target("http://localhost:8080/ReactiveClientEnterprise/userResource");
    }

    @PreDestroy
    public void destroy() {
        if (client != null) {
            client.close();
        }
    }

    public CompletionStage<Response> getResponse() {
        return webTarget.request(MediaType.APPLICATION_JSON).rx().get();
    }
}
