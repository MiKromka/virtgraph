package pl.edu.agh.iosr.virtgraph.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HostTest {

	@Test
	public void shouldAddVms() {
		// given
		Host host = new Host("hostName", "hostAddress");
		VirtualMachine vm1 = new VirtualMachine("vm1", "id1", true);
		VirtualMachine vm2 = new VirtualMachine("vm2", "id2", false);
		host.vm(vm1);
		host.vm(vm2);

		// when
		VMList vmList = host.vms();

		// then
		assertEquals(2, vmList.getVms().size());
		assertTrue(vmList.getVms().contains(vm1));
		assertTrue(vmList.getVms().contains(vm2));
	}
}
