package pl.edu.agh.iosr.virtgraph.controller;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.model.Host;
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

	@POST
	@Path("/host")
	@Consumes("application/xml")
	public Response dummyHostRegistration(Host host) {
		URI dummyURI = UriBuilder.fromUri("dummy").build();
		return Response.created(dummyURI).build();
	}

}
