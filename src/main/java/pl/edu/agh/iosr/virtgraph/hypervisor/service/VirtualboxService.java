package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import pl.edu.agh.iosr.virtgraph.hypervisor.threads.ServiceRunnerThread;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Service
public class VirtualboxService implements VirtualMachineService,
        ApplicationContextAware {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(VirtualboxService.class);
    private ApplicationContext applicationContext = null;

    /* riddiculous, but needs to be done this way for the spring autowiring to work */

    /*
     * @Autowired private VirtualboxManagerWrapper vBWrapper;
     */
    @Override
    public void start(VirtualMachine vm) {
        // TODO implement me
        LOGGER.debug("Starting a virtualmachine(not implemented yet) :" + vm);
    }

    @Override
    public void startService(String vmId,
            pl.edu.agh.iosr.virtgraph.model.Service service) {

        // /FIXME: there must be a better way to do this!
        // so, so ugly ;/
        ServiceRunnerThread runner = applicationContext
                .getBean(ServiceRunnerThread.class);
        runner.setService(service);
        runner.setVmId(vmId);

        new Thread(runner).start();
    }

    @Override
    public void stopService(pl.edu.agh.iosr.virtgraph.model.Service service) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        this.applicationContext = ctx;
        // TODO Auto-generated method stub

    }

}
