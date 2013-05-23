package pl.edu.agh.iosr.virtgraph.controller;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.hasXPath;

import org.junit.Test;

/* should defilitely use static imports, but fucking eclipse diapproves */
public class DummyControllerIntegrationTest {
    @Test
    public void dummyTest() {
    	expect().body(hasXPath("/paragonZaLas/test[text()='test']")).when().get("/virtgraph/dummy/tryme/0");
    }

}
