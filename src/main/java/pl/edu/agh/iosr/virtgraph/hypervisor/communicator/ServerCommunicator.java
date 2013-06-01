package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.CouldNotRegisterException;
import pl.edu.agh.iosr.virtgraph.model.Service;

public interface ServerCommunicator {
	public void registerService(Service service)
			throws CouldNotRegisterException;

	void registerHost() throws CouldNotRegisterException;
}
