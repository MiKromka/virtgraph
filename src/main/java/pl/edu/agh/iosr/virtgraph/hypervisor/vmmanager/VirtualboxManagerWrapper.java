package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import javax.annotation.PostConstruct;

import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.VirtualBoxManager;

import pl.edu.agh.iosr.virtgraph.properties.Properties;

//No longer used, to be removed once the new version works
//@Component
public class VirtualboxManagerWrapper {

	// TODO: add appropriate logs if connection to hypervisor fails/is disabled

	private VirtualBoxManager vBoxManager;
	private IVirtualBox vBox = null;

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

		}

	}

}
