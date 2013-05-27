package pl.edu.agh.iosr.virtgraph.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.edu.agh.iosr.virtgraph.model.ParagonZaLas;

@Service
public class ServerServiceImpl implements ServerService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerServiceImpl.class);

	@Override
	public ParagonZaLas zaplacZaLas(int ile) {
		LOGGER.debug("Gdzie pieniądze są za las?!");
		return new ParagonZaLas(ile, "Server");
	}
}
