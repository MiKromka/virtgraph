package pl.edu.agh.iosr.virtgraph.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ServiceListTest {
	private static final List<String> STOP_ARGS = Arrays.asList("stopArg1",
			"stopArg2");
	private static final List<String> START_ARGS = Arrays.asList("startArg1",
			"startArg2");
	private static final String STOP_COMMAND = "stopCommand";
	private static final String RUN_COMMAND = "runCommand";
	private static final String NAME = "name";
	private static final String STOP_COMMAND_2 = "stopCommand2";
	private static final String RUN_COMMAND_2 = "runCommand2";
	private static final String NAME_2 = "name2";

	@Test
	public void shouldAddAndReturnServices() {
		// given
		ServiceList hl = new ServiceList();
		Service service1 = new Service(NAME, RUN_COMMAND, STOP_COMMAND, false,
				START_ARGS, STOP_ARGS);
		Service service2 = new Service(NAME_2, RUN_COMMAND_2, STOP_COMMAND_2,
				true, START_ARGS, STOP_ARGS);
		hl.addService(service1);
		hl.addService(service2);

		// when
		List<Service> services = hl.getServices();

		// then
		assertEquals(2, services.size());
		assertTrue(services.contains(service1));
		assertTrue(services.contains(service2));
	}

}
