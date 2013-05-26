package pl.edu.agh.iosr.virtgraph.hypervisor.communicator;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.CouldNotRegisterException;

public interface ServerCommunicator {
	public void registerHost() throws CouldNotRegisterException;
}
