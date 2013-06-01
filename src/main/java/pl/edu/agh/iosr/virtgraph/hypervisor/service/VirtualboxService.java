package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.VirtualBoxManager;

import pl.edu.agh.iosr.virtgraph.hypervisor.communicator.ServerCommunicator;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@Service
public class VirtualboxService implements VirtualMachineService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(VirtualboxService.class);

	private VirtualBoxManager vBoxManager;
	private IVirtualBox vBox;

	@Autowired
	ServerCommunicator srvCommunicator;

	@PostConstruct
	public void init() {
		vBoxManager = VirtualBoxManager.createInstance(null);
		if (Properties.isConnectToVirtualboxOnStartup()) {
			String user = Properties.getVirtualMachineUser();
			String pass = Properties.getVirtualMachinePassword();
			String address = Properties.getVirtualboxHypervisorAddress();
			vBoxManager.connect(address, user, pass);
			vBox = vBoxManager.getVBox();
		}
	}

	@Override
	public void start(VirtualMachine vm) {
		// TODO implement me
		LOGGER.debug("Starting a virtualmachine(not implemented yet) :" + vm);
	}

	@Override
	public void startService(pl.edu.agh.iosr.virtgraph.model.Service service) {
		// TODO finish me

	}

	@Override
	public void stopService(pl.edu.agh.iosr.virtgraph.model.Service service) {
		// TODO Auto-generated method stub

	}

}
