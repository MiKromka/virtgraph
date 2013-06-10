package pl.edu.agh.iosr.virtgraph.server.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.ParagonZaLas;
import pl.edu.agh.iosr.virtgraph.server.service.ServerService;

@Controller
@Path("/server")
public class ServerController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ServerController.class);
    @Autowired
    ServerService serverService;

    @GET
    @Produces("application/xml")
    @Path("/tryme/{ile}")
    public ParagonZaLas zaplacZaLas(@PathParam("ile") int ile) {
        LOGGER.info("Place za las {}", ile);
        return serverService.zaplacZaLas(ile);
    }

    @POST
    @Path("/hosts")
    @Consumes("application/xml")
    public Response registerHost(Host host) {
        LOGGER.info("registering host with name: {}", host.getName());
        return generateUriFromServiceResponse(serverService.registerHost(host));
    }

    @GET
    @Produces("application/xml")
    @Path("/hosts/")
    public HostList getHosts() {
        LOGGER.info("returning hostList");
        return serverService.getHostList();
    }

    private Response generateUriFromServiceResponse(String serviceResponse) {
        return Response.created(UriBuilder.fromUri(serviceResponse).build())
                .build();
    }

}
