package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.iosr.virtgraph.model.Service;

@Component
public class DummyStateProvider implements StateProvider {

	@Override
	public List<Service> getAvailableServices() {
		// FIXME!
		ArrayList<Service> serviceList = new ArrayList<Service>();
		Service sshdService = new Service("sshd-ubuntu",
				"sudo service ssh start", "sudo service ssh stop", true);
		serviceList.add(sshdService);
		return serviceList;
	}
}
