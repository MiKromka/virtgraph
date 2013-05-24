package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Service
public class VirtualMachineServiceImpl implements VirtualMachineService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(VirtualMachineServiceImpl.class);

    public void start(VirtualMachine vm) {
        LOGGER.debug("Starting a virtualmachine :" + vm);
    }

}
