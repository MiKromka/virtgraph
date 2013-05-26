package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.properties.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Component
public class RESTServerCommunicator implements ServerCommunicator{

	@Override
	public void registerHost() {
		URI baseURI =  UriBuilder.fromUri(Properties.getServerAddress()).build();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(baseURI);
		service.path("host").post(ClientResponse.class);
/*		// Get plain text // Get plain text
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_HTML).get(String.class));
*/
	}
}
