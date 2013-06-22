package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NotRegisteredException;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface ServerCommunicator {
	public void registerVm(VirtualMachine vm) throws NotRegisteredException;

	public String registerHost() throws NotRegisteredException;

	void registerService(String vmid, Service service)
			throws NotRegisteredException;
}
