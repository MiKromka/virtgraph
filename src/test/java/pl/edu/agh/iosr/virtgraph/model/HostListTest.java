package pl.edu.agh.iosr.virtgraph.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class HostListTest {
	@Test
	public void shouldAddAndReturnHosts() {
		// given
		HostList hl = new HostList();
		Host host1 = new Host("hostName1", "hostAddress1");
		Host host2 = new Host("hostName2", "hostAddress2");
		hl.addHost(host1);
		hl.addHost(host2);

		// when
		List<Host> hosts = hl.getHosts();

		// then
		assertEquals(2, hosts.size());
		assertTrue(hosts.contains(host1));
		assertTrue(hosts.contains(host2));
	}

}
