package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.model.Service;

@Component
public class DummyStateProvider implements StateProvider {

	@Override
	public List<Service> getAvailableServices() {
		// FIXME!
		ArrayList<Service> serviceList = new ArrayList<Service>();
		serviceList.add(new Service());
		return serviceList;
	}
}
