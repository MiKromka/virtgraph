package pl.edu.agh.iosr.virtgraph.server.service;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.ParagonZaLas;

public interface ServerService {
	public ParagonZaLas zaplacZaLas(int ile);

	public String registerHost(Host host);

	public HostList getHostList();

}
