package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NoSuchVMException;
import pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager.VirtualboxManagerWrapper;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Service
public class VirtualboxService implements VirtualMachineService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(VirtualboxService.class);

    @Autowired
    private VirtualboxManagerWrapper vBWrapper;

    @Override
    public void start(VirtualMachine vm) {
        // TODO implement me
        LOGGER.debug("Starting a virtualmachine(not implemented yet) :" + vm);
    }

    @Override
    public void startService(String vmId,
            pl.edu.agh.iosr.virtgraph.model.Service service) {
        try {
            vBWrapper.startService(vmId, service);
        } catch (NoSuchVMException e) {
            e.printStackTrace();
        }
        // TODO update the state
    }

    @Override
    public void stopService(pl.edu.agh.iosr.virtgraph.model.Service service) {
        // TODO Auto-generated method stub

    }

}
