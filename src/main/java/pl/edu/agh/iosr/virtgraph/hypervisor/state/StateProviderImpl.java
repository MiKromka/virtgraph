package pl.edu.agh.iosr.virtgraph.hypervisor.state;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.hypervisor.communicator.ServerCommunicator;
import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NotRegisteredException;
import pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager.VirtualboxManagerWrapper;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@Component
public class StateProviderImpl implements StateProvider {

	@Autowired
	ServerCommunicator srvComm;

	@Autowired
	VirtualboxManagerWrapper vBoxManagerWrapper;

	private String hostAddress = null;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(StateProviderImpl.class);

	private Map<String, VirtualMachine> VMs;

	// FIXME - move initialization to one place (either here or
	// ServerCommunicator)
	@PostConstruct
	public void init() throws NotRegisteredException {
		if (Properties.isRegisterOnStartup()) {
			// FIXME: don't save the host address, use the knowledge about how
			// it
			hostAddress = srvComm.registerHost();
			LOGGER.debug("HOST_ADDRESS is: " + hostAddress);
		} else {
			LOGGER.debug("REGISTER_ON_STARTUP property is not set."
					+ "Not registering the host");
		}

		if (Properties.isConnectToVirtualboxOnStartup()) {
			updateState();
		} else {
			LOGGER.debug("CONNECT_TO_VIRTUALBOX_ON_STARTUP property is not set."
					+ "Not updating state");
		}
		VMs = vBoxManagerWrapper.getVMs();

		if (Properties.isEnableVmRegistration()) {
			for (VirtualMachine vm : VMs.values()) {
				srvComm.registerVm(vm);
				// FIXME!! use some reasonable service reposiory, maybe a
				// database?
				if (Properties.isEnableServiceRegistration()
						&& vm.getName().equals("virtgraph-slave-arch")) {
					LinkedList<String> startArgs = new LinkedList<String>();
					startArgs.add("start");
					startArgs.add("sshd");
					LinkedList<String> stopArgs = new LinkedList<String>();
					stopArgs.add("stop");
					stopArgs.add("sshd");
					Service service = new Service("sshd", "/usr/bin/systemctl",
							"/usr/bin/systemctl", false, startArgs, stopArgs);
					srvComm.registerService(vm.getId(), service);
				} else {
					LOGGER.debug("ENABLE_SERVICE_REGISTRATION property is not set."
							+ "Not registering vms");
				}
			}

		} else {
			LOGGER.debug("ENABLE_VM_REGISTRATION property is not set."
					+ "Not registering vms");
		}

	}

	@Override
	public String getHostAddress() throws NotRegisteredException {
		if (hostAddress == null) {
			throw new NotRegisteredException();
		} else {
			return hostAddress;
		}

	}

	@Override
	public List<Service> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<VirtualMachine> getVMs() {
		return VMs.values();
	}

	@Override
	public VirtualMachine getVM(String id) {
		return VMs.get(id);
	}

	@Override
	public void updateService(String vmid, Service service) {
		// TODO Auto-generated method stub
		System.out.println("StateProvider received a service update");

		try {
			srvComm.registerService(vmid, service);
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateState();

	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		LOGGER.debug("Update received, refreshing the state");
		VMs = vBoxManagerWrapper.getVMs();

	}

	@Override
	public void updateVM(VirtualMachine vm) {
		// TODO Auto-generated method stub
		try {
			srvComm.registerVm(vm);
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("StateProvider received a vm update");
		updateState();

	}
}
