package pl.edu.agh.iosr.virtgraph.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.edu.agh.iosr.virtgraph.dao.DataAccessObject;
import pl.edu.agh.iosr.virtgraph.dao.InMemoryDAO;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@org.springframework.stereotype.Service
public class ServerServiceImpl implements ServerService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerServiceImpl.class);
	private final DataAccessObject dao = new InMemoryDAO();

	@Override
	public String registerHost(Host host) {
		String hostName = dao.registerHost(host);
		return Properties.getServerAddress() + "hosts/" + hostName;
	}

	@Override
	public HostList getHostList() {
		return dao.getHostList();
	}

	@Override
	public String registerService(String hostName, String vmId, Service service) {
		dao.registerService(hostName, vmId, service);
		return Properties.getServerAddress() + "hosts/" + hostName + "vms/"
				+ vmId + "/" + service.getName();
	}

	@Override
	public String registerVMForHost(String hostName, VirtualMachine vm) {
		dao.registerVMForHost(hostName, vm);
		return Properties.getServerAddress() + "hosts/" + hostName + "vms/"
				+ vm.getId();
	}

	@Override
	public VMList getVMListForHost(String hostName) {
		return dao.getVMListForHost(hostName);
	}

	@Override
	public ServiceList getServiceList(String hostName, String vmId) {
		return dao.getServiceList(hostName, vmId);
	}

}
