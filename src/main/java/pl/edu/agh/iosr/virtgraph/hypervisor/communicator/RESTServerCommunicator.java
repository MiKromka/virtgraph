package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NotRegisteredException;
import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Component
public class RESTServerCommunicator implements ServerCommunicator {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(RESTServerCommunicator.class);

	@Autowired
	private StateProvider stateProvider;

	@Override
	public String registerHost() throws NotRegisteredException {
		/* TODO: add sensible properties to the Host instance */
		Host host = new Host(Properties.getHostName(),
				Properties.getSelfAddress());

		URI baseURI = UriBuilder.fromUri(Properties.getServerAddress()).build();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(baseURI);
		ClientResponse response = service.path("hosts")
				.type(MediaType.APPLICATION_XML).entity(host)
				.post(ClientResponse.class);
		if (response.getStatus() >= 300) {
			LOGGER.debug("Failed to register on the server");
			throw new NotRegisteredException();
		} else {
			LOGGER.debug("Successfully registered on the server. Response status code:"
					+ response.getStatus());
		}
		return response.getLocation().toString();
		/*
		 * if (Properties.isEnableVmRegistration()) { for (VirtualMachine vm :
		 * stateProvider.getVMs()) { registerVm(vm); } } else {
		 * LOGGER.debug("ENABLE_VM_REGISTRATION property is not set."); }
		 * 
		 * if (Properties.isEnableServiceRegistration()) { for (Service s :
		 * stateProvider.getServices()) { registerService(s); } } else {
		 * LOGGER.debug("ENABLE_SERVICE_REGISTRATION property is not set."); }
		 */
		// FIXME prvide a way to register multipne services with one message
		// (or at least one registerService() call)

		/*
		 * // Get plain text // Get plain text
		 * System.out.println(service.path("rest"
		 * ).path("hello").accept(MediaType.TEXT_PLAIN).get(String.class)); //
		 * Get XML
		 * System.out.println(service.path("rest").path("hello").accept(MediaType
		 * .TEXT_XML).get(String.class)); // The HTML
		 * System.out.println(service.
		 * path("rest").path("hello").accept(MediaType
		 * .TEXT_HTML).get(String.class));
		 */
	}

	@Override
	public void registerVm(VirtualMachine vm) throws NotRegisteredException {
		URI baseURI = UriBuilder.fromUri(Properties.getServerAddress()).build();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webRes = client.resource(baseURI);
		ClientResponse response = webRes.path("hosts")
				.path(Properties.getHostName()).path("vms")
				.type(MediaType.APPLICATION_XML).entity(vm)
				.post(ClientResponse.class);
		if (response.getStatus() >= 300) {
			LOGGER.debug("Failed to register the vm on the server");
			throw new NotRegisteredException();
		} else {
			LOGGER.debug("Successfully registered the vm on the server. Response status code:"
					+ response.getStatus());
		}
	}

	@Override
	public void registerService(String vmid, Service service)
			throws NotRegisteredException {
		// TODO: add proper error messages in case of failure
		URI baseURI = UriBuilder.fromUri(Properties.getServerAddress()).build();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webRes = client.resource(baseURI);
		ClientResponse response = webRes.path("hosts")
				.path(Properties.getHostName()).path("vms").path(vmid)
				.path("services").type(MediaType.APPLICATION_XML)
				.entity(service).post(ClientResponse.class);
		if (response.getStatus() >= 300) {
			LOGGER.debug("Failed to register the service on the server");
			throw new NotRegisteredException();
		} else {
			LOGGER.debug("Successfully registered the service on the server. Response status code:"
					+ response.getStatus());
		}
	}

}
