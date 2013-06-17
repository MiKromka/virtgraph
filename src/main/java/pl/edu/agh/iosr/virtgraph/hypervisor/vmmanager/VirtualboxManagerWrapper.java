package pl.edu.agh.iosr.virtgraph.hypervisor.vmmanager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.virtualbox_4_2.IConsole;
import org.virtualbox_4_2.IGuest;
import org.virtualbox_4_2.IGuestProcess;
import org.virtualbox_4_2.IGuestSession;
import org.virtualbox_4_2.IMachine;
import org.virtualbox_4_2.IProgress;
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.LockType;
import org.virtualbox_4_2.MachineState;
import org.virtualbox_4_2.ProcessCreateFlag;
import org.virtualbox_4_2.ProcessStatus;
import org.virtualbox_4_2.VirtualBoxManager;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NoSuchVMException;
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
		} else {
			LOGGER.debug(" no connection to virtualbox hypervisor. "
					+ "CONNECT_TO_VIRTUALBOX_ON_STARTUP  is not be set");
		}
	}

	public Map<String, VirtualMachine> getVMs() {
		Map<String, VirtualMachine> VMs = new HashMap<String, VirtualMachine>();
		if (vBox != null) {
			for (IMachine vm : vBox.getMachines()) {
				boolean running = false;
				if (vm.getState().equals(MachineState.Running)) {
					running = true;
				}
				VMs.put(vm.getId(), new VirtualMachine(vm.getName(),
						vm.getId(), running));
			}
		} else {
			LOGGER.debug(" no connection to virtualbox hypervisor. "
					+ "connect_to_virtualbox_on_startup might not be set"
					+ " not gathering data on virtual machines.");
		}
		return VMs;
	}

	// FIXME: use either only the name or only the id. This might get confusing.
	private boolean vmExists(String name) {
		return getVM(name) != null;
	}

	private IMachine getVM(String name) {
		if (vBox != null) {
			for (IMachine vm : vBox.getMachines()) {
				if (vm.getName().equals(name)) {
					return vm;
				}
			}
		} else {
			LOGGER.debug(" no connection to virtualbox hypervisor. "
					+ "connect_to_virtualbox_on_startup might not be set"
					+ " not gathering data on virtual machines.");
		}
		return null;
	}

	/*
	 * vm - model VirtualMachine, vboxVM - virtualbox IMachine. This might be a
	 * little confusing, focus!
	 */
	public boolean toggleMachine(VirtualMachine vm) throws NoSuchVMException {
		String name = vm.getName();
		System.out.println("\nAttempting to start VM '" + name + "'");
		// TODO: attach the sources, find out what the arguments mean. Is the
		// third argument a timeout?
		// TODO: trigger state update if anything goes wrong
		if (!vmExists(name)) {
			throw new NoSuchVMException();
		}
		IMachine vboxVM = getVM(name);
		ISession session = null;
		if (vm.isRunning()) {
			return vBoxManager.startVm(name, null, 7000);
		} else {
			try {
				session = vBoxManager.getSessionObject();
				vboxVM.lockMachine(session, LockType.Shared);

				IConsole console = session.getConsole();
				IProgress progress = console.powerDown();
				progress.waitForCompletion(10000);
				if (progress.getResultCode() != 0) {
					return false;
				}
				return true;

			} finally {
				if (session != null) {
					session.unlockMachine();
				}
			}
		}
	}

	public boolean toggleService(String vmId,
			pl.edu.agh.iosr.virtgraph.model.Service service)
			throws NoSuchVMException {

		ProcessStatus exitStatus = null;

		// TODO finish me, check me, use the sent service
		IMachine machine = null;
		for (IMachine vm : vBox.getMachines()) {
			if (vm.getId().equals(vmId)) {
				machine = vm;
			}
		}
		if (machine == null) {
			throw new NoSuchVMException();
		}

		ISession session = null;
		IGuestSession gsession = null;
		try {
			session = vBoxManager.getSessionObject();
			machine.lockMachine(session, LockType.Shared);
			String user = Properties.getInnerVirtualMachineUser();
			String pass = Properties.getInnerVirtualMachinePassword();
			IConsole console = session.getConsole();
			IGuest guest = console.getGuest();
			gsession = guest.createSession(user, pass, "", "");
			List<String> env = new LinkedList<String>();
			List<ProcessCreateFlag> flags = new LinkedList<ProcessCreateFlag>();
			flags.add(ProcessCreateFlag.ExpandArguments);
			flags.add(ProcessCreateFlag.IgnoreOrphanedProcesses);

			/*
			 * for (String arg : service.getArgs()) { args.add(arg); }
			 */env.add("PATH=/bin:/usr/bin");

			List<String> args;
			String command;
			if (service.isStart()) {
				command = service.getRunCommand();
				args = service.getStartArgs();
			} else {
				command = service.getStopCommand();
				args = service.getStopArgs();
			}

			IGuestProcess process = gsession.processCreate(command, args, env,
					flags, 0l);
			// FIXME: move execution to another thread. wait as long as it
			// takes. possibly use some kind of ThreadPool

			Thread.sleep(300);
			System.out.print("guest process state:");
			System.out.println(process.getStatus());
			System.out.println(process.getExitCode());
			exitStatus = process.getStatus();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (gsession != null) {
				gsession.close();
			}
			session.unlockMachine();
		}
		// TODO: other ways of determining if the service was run correctly
		// might be more adequate
		return exitStatus == org.virtualbox_4_2.ProcessStatus.TerminatedNormally;
	}
}
