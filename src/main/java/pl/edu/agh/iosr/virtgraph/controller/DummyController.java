package pl.edu.agh.iosr.virtgraph.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.model.ParagonZaLas;
import pl.edu.agh.iosr.virtgraph.service.DummyService;

@Controller
@Path("/dummy")
public class DummyController {

    @Autowired
    DummyService dummyService;

    @GET
    @Produces("application/xml")
    @Path("/tryme/{ile}")
    public ParagonZaLas zaplacZaLas(@PathParam("ile") int ile) {
        return dummyService.zaplacZaLas(ile);
    }

}
