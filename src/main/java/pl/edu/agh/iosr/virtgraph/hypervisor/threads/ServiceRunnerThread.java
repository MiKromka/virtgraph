package pl.edu.agh.iosr.virtgraph.hypervisor.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NoSuchVMException;
import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager.VirtualboxManagerWrapper;
import pl.edu.agh.iosr.virtgraph.model.Service;

/*Need to use spring to make autowiring work.*/
@Component
@Scope("prototype")
public class ServiceRunnerThread implements Runnable {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ServiceRunnerThread.class);

	@Autowired
	VirtualboxManagerWrapper vBoxWrapper;

	@Autowired
	StateProvider stateProvider;

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	private String vmId;
	private Service service;

	public ServiceRunnerThread() {
		//
	}

	public ServiceRunnerThread(String vmId, Service service) {
		this.vmId = vmId;
		this.service = service;
	}

	@Override
	public void run() {
		// TODO: implement me: run the service, notify the StateProvider
		LOGGER.debug("Started a thread for running a service");
		System.out.println("Service runner thread runs...");
		boolean success = false;
		try {
			success = vBoxWrapper.toggleService(vmId, service);
		} catch (NoSuchVMException e) {
			e.printStackTrace();
		}
		if (success) {
			stateProvider.updateService(vmId, service);
		}
	}
}
