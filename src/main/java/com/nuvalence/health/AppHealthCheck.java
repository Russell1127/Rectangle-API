package com.nuvalence.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Strings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AppHealthCheck extends HealthCheck {
    private final Client client;

    public AppHealthCheck(Client client) {
        super();
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        WebTarget webTarget = client.target("http://localhost:8080/api/rectangles");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String pong = response.readEntity(String.class);
        if(!Strings.isNullOrEmpty(pong) && pong.equals("pong")) {
            return Result.healthy();
        }
        return Result.unhealthy("API Failed");
    }
}
