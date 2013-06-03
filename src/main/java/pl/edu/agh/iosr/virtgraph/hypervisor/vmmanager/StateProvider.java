package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.List;

import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface StateProvider {

	public List<Service> getAvailableServices();

	public List<VirtualMachine> getAvailableVMs();
}
