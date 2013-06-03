package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.CouldNotRegisterException;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface ServerCommunicator {
	public void registerService(Service service)
			throws CouldNotRegisterException;

	public void registerVm(VirtualMachine vm) throws CouldNotRegisterException;

	void registerHost() throws CouldNotRegisterException;
}
