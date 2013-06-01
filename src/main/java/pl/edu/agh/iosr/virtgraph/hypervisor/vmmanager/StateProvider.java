package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.List;

import pl.edu.agh.iosr.virtgraph.model.Service;

public interface StateProvider {

	public List<Service> getAvailableServices();
}
