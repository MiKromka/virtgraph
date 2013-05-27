package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virtualbox_4_2.VirtualBoxManager;

import pl.edu.agh.iosr.virtgraph.hypervisor.communicator.ServerCommunicator;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Service
public class VirtualboxService implements VirtualMachineService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(VirtualboxService.class);

	@Autowired
	VirtualBoxManager vBoxManager;

	@Autowired
	ServerCommunicator srvCommunicator;

	@Override
	public void start(VirtualMachine vm) {
		LOGGER.debug("Starting a virtualmachine :" + vm);
	}

}
