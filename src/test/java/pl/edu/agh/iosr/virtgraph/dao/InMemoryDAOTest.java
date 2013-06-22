package pl.edu.agh.iosr.virtgraph.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public class InMemoryDAOTest {
	private final InMemoryDAO dao = new InMemoryDAO();

	@Test
	public void shouldRegisterHost() {
		// given
		Host host = new Host("name", "address");
		dao.registerHost(host);

		// when
		HostList hl = dao.getHostList();

		// then
		assertNotNull(hl);
		assertEquals(1, hl.getHosts().size());
		assertEquals(host, hl.getHosts().get(0));
	}

	@Test
	public void shouldRegisterVM() {
		// given
		VirtualMachine vm = new VirtualMachine("name1", "ïd1", false);
		dao.registerVMForHost("hostName", vm);

		// when
		VMList vl = dao.getVMListForHost("hostName");

		// then
		assertNotNull(vl);
		assertEquals(1, vl.getVms().size());
		assertTrue(vl.getVms().contains(vm));
	}

	@Test
	public void shouldRegisterServices() {
		// given
		Service service = new Service("name", "runCommand", "stopCommand",
				false, Arrays.asList("startArg1", "startArg2"), Arrays.asList(
						"stopArg1", "stopArg2"));
		dao.registerService("hostName", "vmId", service);

		// when
		ServiceList sl = dao.getServiceList("hostName", "vmId");

		// then
		assertNotNull(sl);
		assertEquals(1, sl.getServices().size());
		assertEquals(service, sl.getServices().get(0));
	}

}
