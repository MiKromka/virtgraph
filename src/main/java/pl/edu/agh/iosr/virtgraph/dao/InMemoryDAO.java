package pl.edu.agh.iosr.virtgraph.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public class InMemoryDAO implements DataAccessObject {
	private final Map<String, Host> hosts = new HashMap<String, Host>();
	private final Map<String, VirtualMachine> vms = new HashMap<String, VirtualMachine>();
	private final Map<String, Service> services = new HashMap<String, Service>();

	@Override
	public String registerHost(Host host) {
		System.out.println("putting host: " + host.getName());
		hosts.put(host.getName(), host);
		return host.getName();
	}

	@Override
	public HostList getHostList() {
		for (Host s : hosts.values()) {
			System.out.println(s.getName());
		}
		return new HostList().setHosts(hosts.values());
	}

	@Override
	public String registerService(String hostName, String vmId, Service service) {
		services.put(hostName + "#" + vmId + "#" + service.getName(), service);
		return service.getName();
	}

	@Override
	public String registerVMForHost(String hostName, VirtualMachine vm) {
		vms.put(hostName + "#" + vm.getId(), vm);
		return vm.getId();
	}

	@Override
	public VMList getVMListForHost(String hostName) {
		VMList vmlist = new VMList();
		for (Entry<String, VirtualMachine> entries : vms.entrySet()) {
			if (entries.getKey().startsWith(hostName)) {
				vmlist.addVm(entries.getValue());
			}
		}
		return vmlist;
	}

	@Override
	public ServiceList getServiceList(String hostName, String vmId) {
		ServiceList serviceList = new ServiceList();
		for (Entry<String, Service> entries : services.entrySet()) {
			if (entries.getKey().startsWith(hostName + "#" + vmId)) {
				serviceList.addService(entries.getValue());
			}
		}
		return serviceList;
	}

	@Override
	public HostList getHostListWithService(Service service) {
		HostList hl = new HostList();
		for (Entry<String, Service> entry : services.entrySet()) {
			// hostName#vmId#serviceName
			String[] tmp = entry.getKey().split("#");
			if (service.equals(entry.getValue())) {
				hl.addHost(hosts.get(tmp[0]));
			}
		}

		return hl;
	}

	@Override
	public VMList getVMListForHostWithService(Service service, String hostName) {
		VMList vmlist = new VMList();
		for (Entry<String, Service> entry : services.entrySet()) {
			// hostName#vmId#serviceName
			String[] tmp = entry.getKey().split("#");
			if (service.equals(entry.getValue()) && tmp[0].equals(hostName)) {
				vmlist.addVm(vms.get(tmp[1]));
			}
		}
		return vmlist;
	}

	@Override
	public ServiceList getServiceList() {
		return new ServiceList(new ArrayList<Service>(services.values()));
	}
}
