package com.nuvalence.controllers;

import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/status")
public class HealthCheckController {
    private HealthCheckRegistry registry;

    public HealthCheckController(HealthCheckRegistry registry) {
        this.registry = registry;
    }

    @GET
    public Set<Map.Entry<String, Result>> getStatus(){
        return registry.runHealthChecks().entrySet();
    }
}
