package pl.edu.agh.iosr.virtgraph.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.service.DummyService;

@Controller
@Path("/dummy")
public class DummyController {

    @Autowired
    DummyService dummyService;

    @GET
    @Path("/tryme")
    public Response zaplacZaLas() {
        String result = dummyService.zaplacZaLas();
        return Response.status(200).entity(result).build();
    }

}
