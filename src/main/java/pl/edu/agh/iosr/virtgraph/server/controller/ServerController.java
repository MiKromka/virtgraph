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
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
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

	@GET
	@Path("/hosts/{hostName}/vms/{vmId}/services")
	@Produces("application/xml")
	public ServiceList getServices(@PathParam("hostName") String hostName,
			@PathParam("vmId") String vmId) {
		LOGGER.info("returning ServiceList for host:{}, and for VM: {}",
				hostName, vmId);
		return serverService.getServiceList(hostName, vmId);
	}

	@GET
	@Path("/hosts/{hostName}/vms")
	@Produces("application/xml")
	public VMList getVMs(@PathParam("hostName") String hostName) {
		LOGGER.info("returning VMList for host:{}", hostName);
		return serverService.getVMListForHost(hostName);
	}

	@GET
	@Produces("application/xml")
	@Path("/hosts")
	public HostList getHosts() {
		LOGGER.info("returning hostList");
		return serverService.getHostList();
	}

	@POST
	@Path("/hosts")
	@Consumes("application/xml")
	public Response registerHost(Host host) {
		LOGGER.info("registering host with name: {}", host.getName());
		return generateUriFromServiceResponse(serverService.registerHost(host));
	}

	@POST
	@Path("/hosts/{hostName}/vms")
	@Consumes("application/xml")
	public Response registerVM(@PathParam("hostName") String hostName,
			VirtualMachine vm) {
		LOGGER.info("registering vms for host with name: {}", hostName);
		return generateUriFromServiceResponse(serverService.registerVMForHost(
				hostName, vm));
	}

	@POST
	@Path("/hosts/{hostName}/vms/{vmId}/services")
	@Consumes("application/xml")
	public Response registerService(@PathParam("hostName") String hostName,
			@PathParam("vmId") String vmId, Service service) {
		LOGGER.info("registering vms for host with name: {}", hostName);
		return generateUriFromServiceResponse(serverService.registerService(
				hostName, vmId, service));
	}

	private Response generateUriFromServiceResponse(String serviceResponse) {
		return Response.created(UriBuilder.fromUri(serviceResponse).build())
				.build();
	}

}
