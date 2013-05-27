package pl.edu.agh.iosr.virtgraph.controller;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasXPath;

import org.junit.Test;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

/* should defilitely use static imports, but fucking eclipse diapproves */
public class DummyControllerIntegrationTest {

	@Test
	public void dummyTest() {
		expect().body(hasXPath("/paragonZaLas/test[text()='test']")).when()
				.get("/virtgraph/dummy/tryme/0");
	}

	@Test
	public void registerHostTest() {
		Host host = new Host("HostName");
		given().contentType("application/xml").request().body(host).expect()
				.statusCode(201).when()
				.post(Properties.getRelativeServerLocation());
	}

}
