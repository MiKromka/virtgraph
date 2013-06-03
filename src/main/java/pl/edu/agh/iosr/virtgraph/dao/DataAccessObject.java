package pl.edu.agh.iosr.virtgraph.dao;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;

public interface DataAccessObject {
	public int registerHost(Host host);

	public HostList getHostList();
}
