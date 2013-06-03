package pl.edu.agh.iosr.virtgraph.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.edu.agh.iosr.virtgraph.dao.DataAccessObject;
import pl.edu.agh.iosr.virtgraph.dao.InMemoryDAO;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.ParagonZaLas;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@Service
public class ServerServiceImpl implements ServerService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerServiceImpl.class);
	private final DataAccessObject dao = new InMemoryDAO();

	@Override
	public ParagonZaLas zaplacZaLas(int ile) {
		LOGGER.debug("Gdzie pieniądze są za las?!");
		return new ParagonZaLas(ile, "Server");
	}

	@Override
	public String registerHost(Host host) {
		int id = dao.registerHost(host);
		return Properties.getRelativeServerLocation() + id;
	}

	@Override
	public HostList getHostList() {
		return dao.getHostList();
	}
}
