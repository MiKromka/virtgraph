package pl.edu.agh.iosr.virtgraph.hypervisor.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NoSuchVMException;
import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager.VirtualboxManagerWrapper;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

/*Need to use spring to make autowiring work.*/
@Component
@Scope("prototype")
public class VMToggerThread implements Runnable {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(VMToggerThread.class);

    @Autowired
    VirtualboxManagerWrapper vBoxWrapper;

    @Autowired
    StateProvider stateProvider;

    private VirtualMachine vm;

    public VirtualMachine getVm() {
        return vm;
    }

    public void setVm(VirtualMachine vm) {
        this.vm = vm;
    }

    public VMToggerThread(VirtualMachine vm) {
        this.vm = vm;
    }

    public VMToggerThread() {
        //
    }

    @Override
    public void run() {
        // TODO: implement me: run the service, notify the StateProvider
        LOGGER.debug("Started a thread for running a service");
        System.out.println("Service runner thread runs...");
        boolean success = false;
        try {
            success = vBoxWrapper.toggleMachine(vm);
        } catch (NoSuchVMException e) {
            e.printStackTrace();
        }
        if (success)
            stateProvider.updateVM(vm);
    }
}
