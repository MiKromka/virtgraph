package pl.edu.agh.iosr.virtgraph.hypervisor.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Controller
@Path("/test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestController.class);

	@Autowired
	StateProvider stateProvider;

	@GET
	@Path("/sampleservice")
	@Produces("application/xml")
	public Service sampleService(@PathParam("id") int id) {
		new LinkedList<Service>();
		LinkedList<String> startArgs = new LinkedList<String>();
		startArgs.add("start");
		startArgs.add("sshd");
		LinkedList<String> stopArgs = new LinkedList<String>();
		stopArgs.add("stop");
		stopArgs.add("sshd");
		return new Service("sshd", "/usr/bin/systemctl", "/usr/bin/systemctl",
				true, startArgs, stopArgs);
	}

	@GET
	@Path("/samplehost")
	@Produces("application/xml")
	public Host sampleHost() {
		return new Host("fakename", "fakeaddr");
	}

	@GET
	@Path("/vm")
	@Produces("application/xml")
	public VirtualMachine sampleVm() {
		return new VirtualMachine("samplename", "sampleid", true);

	}

	@GET
	@Path("/hostlist")
	@Produces("application/xml")
	public HostList getHostsX() {
		LinkedList<Host> list = new LinkedList<Host>();
		list.add(new Host("tomek-pc",
				"http://localhost:8080/virtgraph/hypervisor/"));
		HostList hlist = new HostList();
		hlist.setHosts(list);
		return hlist;
	}

	@GET
	@Path("/vmlist")
	@Produces("application/xml")
	public VMList getVMList() {
		return new VMList(stateProvider.getVMs());
	}

	@GET
	@Path("/servicelist")
	@Produces("application/xml")
	public ServiceList getServiceList() {
		LinkedList<Service> list = new LinkedList<Service>();
		LinkedList<String> startArgs = new LinkedList<String>();
		startArgs.add("start");
		startArgs.add("sshd");
		LinkedList<String> stopArgs = new LinkedList<String>();
		stopArgs.add("stop");
		stopArgs.add("sshd");
		list.add(new Service("sshd", "/usr/bin/systemctl",
				"/usr/bin/systemctl", false, startArgs, stopArgs));
		return new ServiceList(list);
	}

	/* Mock the server */
	@GET
	@Path("/hosts/{hostName}/vms/{vmId}/services")
	@Produces("application/xml")
	public ServiceList getServices(@PathParam("hostName") String hostName,
			@PathParam("vmId") String vmId) {

		LOGGER.info("returning ServiceList for host:{}, and for VM: {}",
				hostName, vmId);

		LinkedList<Service> list = new LinkedList<Service>();
		LinkedList<String> startArgs = new LinkedList<String>();
		startArgs.add("start");
		startArgs.add("sshd");
		LinkedList<String> stopArgs = new LinkedList<String>();
		stopArgs.add("stop");
		stopArgs.add("sshd");
		list.add(new Service("sshd", "/usr/bin/systemctl",
				"/usr/bin/systemctl", false, startArgs, stopArgs));
		return new ServiceList(list);
	}

	@GET
	@Path("/hosts/{hostName}/vms")
	@Produces("application/xml")
	public VMList getVMs(@PathParam("hostName") String hostName) {
		LOGGER.info("returning VMList for host:{}", hostName);
		return new VMList(stateProvider.getVMs());
	}

	@GET
	@Produces("application/xml")
	@Path("/hosts")
	public HostList getHosts() {
		LOGGER.info("returning hostList");
		LinkedList<Host> list = new LinkedList<Host>();
		list.add(new Host("tomek-pc",
				"http://localhost:8080/virtgraph/hypervisor/"));
		HostList hlist = new HostList();
		hlist.setHosts(list);
		return hlist;
	}

	@POST
	@Path("/hosts")
	@Consumes("application/xml")
	public Response registerHost(Host host) throws URISyntaxException {
		LOGGER.info("registering host with name: {}", host.getName());
		return Response.created(new URI("localhost")).build();
	}

	@POST
	@Path("/hosts/{hostName}/vms")
	@Consumes("application/xml")
	public Response registerVM(@PathParam("hostName") String hostName,
			VirtualMachine vm) throws URISyntaxException {
		LOGGER.info("registering vms for host with name: {}", hostName);
		return Response.created(new URI("localhost")).build();
	}

	@POST
	@Path("/hosts/{hostName}/vms/{vmId}/services")
	@Consumes("application/xml")
	public Response registerService(@PathParam("hostName") String hostName,
			@PathParam("vmId") String vmId, Service service)
			throws URISyntaxException {
		LOGGER.info("registering vms for host with name: {}", hostName);
		return Response.created(new URI("localhost")).build();
	}

}
