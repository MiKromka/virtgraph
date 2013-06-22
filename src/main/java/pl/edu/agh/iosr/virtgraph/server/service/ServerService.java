package pl.edu.agh.iosr.virtgraph.server.service;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface ServerService {
	public String registerHost(Host host);

	public HostList getHostList();

	public String registerService(String hostName, String vmId, Service service);

	public String registerVMForHost(String hostName, VirtualMachine vm);

	public VMList getVMListForHost(String hostName);

	public ServiceList getServiceList(String hostName, String vmId);
}
