package pl.edu.agh.iosr.virtgraph.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DummyServiceImpl.class);

    public String zaplacZaLas() {
        String msg = "Gdzie pieniądze są za las?!";
        LOGGER.debug(msg);
        return msg;
    }

}
