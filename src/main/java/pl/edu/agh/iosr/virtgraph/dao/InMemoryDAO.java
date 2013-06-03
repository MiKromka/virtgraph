package pl.edu.agh.iosr.virtgraph.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;

public class InMemoryDAO implements DataAccessObject {
	private final Map<Integer, Host> hosts = new HashMap<Integer, Host>();
	private final AtomicInteger lastHost = new AtomicInteger(0);

	@Override
	public int registerHost(Host host) {
		int id = lastHost.incrementAndGet();
		hosts.put(id, host);
		return id;
	}

	@Override
	public HostList getHostList() {
		return new HostList().setHosts(hosts.values());
	}

}
