package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.CouldNotRegisterException;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Component
public class RESTServerCommunicator implements ServerCommunicator{

	private final static Logger LOGGER = LoggerFactory
            .getLogger(RESTServerCommunicator.class);
	
	@Override
	@PostConstruct
	public void registerHost() throws CouldNotRegisterException {
		if (Properties.isRegisterOnStartup())
		{
			/*TODO: add sensible properties to the Host instance*/
			Host host = new Host("hostName");
			
			
			URI baseURI =  UriBuilder.fromUri(Properties.getServerAddress()).build();
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(baseURI);
			ClientResponse response = service.path("host").type(MediaType.APPLICATION_XML)
					.entity(host).post(ClientResponse.class);
			if (response.getStatus() >=300)
			{
				LOGGER.debug("Failed to register on the server");
				throw new CouldNotRegisterException();
			}
			else
				LOGGER.debug("Successfully registered on the server. Response status code:"
						+ response.getStatus());
		}
		else
				LOGGER.debug("REGISTER_ON_STARTUP property is not set.");
		
/*		// Get plain text // Get plain text
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_HTML).get(String.class));
*/
	}
}
