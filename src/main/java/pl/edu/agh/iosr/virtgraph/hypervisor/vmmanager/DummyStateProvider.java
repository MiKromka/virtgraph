package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Component
public class DummyStateProvider implements StateProvider {

	@Override
	public List<Service> getAvailableServices() {
		// FIXME!
		ArrayList<Service> serviceList = new ArrayList<Service>();
		List<String> args = new LinkedList<String>();
		Service sshdService = new Service("sshd-ubuntu",
				"sudo service ssh start", "sudo service ssh stop", true, args);
		serviceList.add(sshdService);
		return serviceList;
	}

	@Override
	public List<VirtualMachine> getAvailableVMs() {
		return new LinkedList<VirtualMachine>();
	}
}
