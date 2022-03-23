package com.nuvalence.controllers;

import com.nuvalence.core.Rectangle;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rectangles")
@Produces(MediaType.APPLICATION_JSON)
public class RectanglesController {
    public RectanglesController() {
        System.out.println("Made it to the Rectangles Controller.");
    }

    @POST
    @Path("/intersect")
    public Response doIntersect(Rectangle[] rectangles) {
        if (isInvalid(rectangles)) {
            return Response.ok(false).build();
        }

        return Response.ok(rectangles[0].intersects(rectangles[1])).build();
    }

    @POST
    @Path("/contained")
    public Response isContained(Rectangle[] rectangles) {
        if (isInvalid(rectangles)) {
            return Response.ok(false).build();
        }

        return Response.ok(rectangles[0].contains(rectangles[1])).build();
    }

    @POST
    @Path("/adjacent")
    public Response isAdjacent(Rectangle[] rectangles) {
        if (isInvalid(rectangles)) {
            return Response.ok(false).build();
        }

        return Response.ok(rectangles[0].adjacent(rectangles[1])).build();
    }

    @GET
    public Response ping() {
        return Response.ok("pong").build();
    }

    private boolean isInvalid(Rectangle[] rectangles) {
        return rectangles[0] == null || rectangles[0].getWidth() == 0 || rectangles[0].getHeight() == 0
                || rectangles[1] == null || rectangles[1].getWidth() == 0 || rectangles[1].getHeight() == 0;
    }
}
