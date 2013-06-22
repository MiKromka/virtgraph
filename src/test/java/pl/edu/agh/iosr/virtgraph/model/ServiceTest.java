package pl.edu.agh.iosr.virtgraph.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ServiceTest {

	private static final List<String> STOP_ARGS = Arrays.asList("stopArg1",
			"stopArg2");
	private static final List<String> START_ARGS = Arrays.asList("startArg1",
			"startArg2");
	private static final String STOP_COMMAND = "stopCommand";
	private static final String RUN_COMMAND = "runCommand";
	private static final String NAME = "name";

	@Test
	public void shouldgetDataFromService() {
		// given
		Service service = new Service(NAME, RUN_COMMAND, STOP_COMMAND, false,
				START_ARGS, STOP_ARGS);

		// when-then
		assertEquals(NAME, service.getName());
		assertEquals(STOP_COMMAND, service.getStopCommand());
		assertEquals(RUN_COMMAND, service.getRunCommand());
		assertEquals(START_ARGS, service.getStartArgs());
		assertEquals(STOP_ARGS, service.getStopArgs());
	}
}
