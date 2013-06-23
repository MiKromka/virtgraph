package pl.edu.agh.iosr.virtgraph.dao;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface DataAccessObject {
	public String registerHost(Host host);

	public String registerService(String hostName, String vmId, Service service);

	public HostList getHostList();

	public ServiceList getServiceList();

	public String registerVMForHost(String hostName, VirtualMachine vm);

	public VMList getVMListForHost(String hostName);

	public ServiceList getServiceList(String hostName, String vmId);

	public HostList getHostListWithService(Service service);

	public VMList getVMListForHostWithService(Service service, String hostName);
}
