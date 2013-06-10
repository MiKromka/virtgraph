package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.VirtualBoxManager;

import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@Component
public class VirtualboxManagerWrapper {

    // TODO: add appropriate logs if connection to hypervisor fails/is disabled

    private VirtualBoxManager vBoxManager;
    private IVirtualBox vBox = null;

    private final static Logger LOGGER = LoggerFactory
            .getLogger(VirtualboxManagerWrapper.class);

    public IVirtualBox getvBox() {
        return vBox;
    }

    @PostConstruct
    public void init() {
        vBoxManager = VirtualBoxManager.createInstance(null);
        if (Properties.isConnectToVirtualboxOnStartup()) {
            String user = Properties.getVirtualMachineUser();
            String pass = Properties.getVirtualMachinePassword();
            String address = Properties.getVirtualboxHypervisorAddress();
            vBoxManager.connect(address, user, pass);
            vBox = vBoxManager.getVBox();
        } else
            LOGGER.debug(" no connection to virtualbox hypervisor. "
                    + "CONNECT_TO_VIRTUALBOX_ON_STARTUP  is not be set");
    }

    public List<VirtualMachine> getVMs() {
        List<VirtualMachine> VMs = new ArrayList<VirtualMachine>();
        if (vBox != null) {
            for (IMachine vm : vBox.getMachines()) {
                VMs.add(new VirtualMachine(vm.getName(), vm.getId()));
            }
        } else
            LOGGER.debug(" no connection to virtualbox hypervisor. "
                    + "connect_to_virtualbox_on_startup might not be set"
                    + " not gathering data on virtual machines.");
        return VMs;
    }
}
