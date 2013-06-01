package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface VirtualMachineService {
	public void start(VirtualMachine vm);

	public void startService(Service service);

	public void stopService(Service service);
}
