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
import org.virtualbox_4_2.ISession;
import org.virtualbox_4_2.IVirtualBox;
import org.virtualbox_4_2.LockType;
import org.virtualbox_4_2.ProcessCreateFlag;
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
        } else
            LOGGER.debug(" no connection to virtualbox hypervisor. "
                    + "CONNECT_TO_VIRTUALBOX_ON_STARTUP  is not be set");
    }

    public Map<String, VirtualMachine> getVMs() {
        Map<String, VirtualMachine> VMs = new HashMap<String, VirtualMachine>();
        if (vBox != null) {
            for (IMachine vm : vBox.getMachines()) {
                VMs.put(vm.getId(),
                        new VirtualMachine(vm.getName(), vm.getId()));
            }
        } else
            LOGGER.debug(" no connection to virtualbox hypervisor. "
                    + "connect_to_virtualbox_on_startup might not be set"
                    + " not gathering data on virtual machines.");
        return VMs;
    }

    public void startService(String vmId,
            pl.edu.agh.iosr.virtgraph.model.Service service)
            throws NoSuchVMException {

        // TODO finish me, check me, use the sent service
        IMachine machine = null;
        for (IMachine vm : vBox.getMachines()) {
            if (vm.getId().equals(vmId))
                machine = vm;
        }
        if (machine == null)
            throw new NoSuchVMException();

        ISession session = null;
        IGuestSession gsession = null;
        try {
            session = vBoxManager.getSessionObject();
            /*
             * System.out.println("session state:"); System.out.println(session.getState());
             * 
             * System.out.println("locking the machine...");
             */machine.lockMachine(session, LockType.Shared);

            /*
             * System.out.println("session state:"); System.out.println(session.getState());
             */
            String user = Properties.getInnerVirtualMachineUser();
            String pass = Properties.getInnerVirtualMachinePassword();

            // System.out.println("creatind guest session...");
            IConsole console = session.getConsole();
            IGuest guest = console.getGuest();
            // arguments 3 and 4 are opional

            gsession = guest.createSession(user, pass, "", "");

            /*
             * System.out.println("guest session state:");
             * 
             * System.out.println(gsession.getDomain()); System.out.println(gsession.getName());
             * System.out.println(gsession.getUser());
             */
            /*
             * Boolean exists = gsession.fileExists("/homek/test/xxx"); System.out.println(exists);
             */// IGuestFile file = gsession.fileOpen("/home/tomek/xxx", "r",
               // "r",
               // 0l,
               // 0l);
               // System.out.println(file.read(0l, 10l));

            List<String> args = new LinkedList<String>();
            List<String> env = new LinkedList<String>();
            List<ProcessCreateFlag> flags = new LinkedList<ProcessCreateFlag>();
            flags.add(ProcessCreateFlag.ExpandArguments);
            flags.add(ProcessCreateFlag.IgnoreOrphanedProcesses);

            for (String arg : service.getArgs()) {
                args.add(arg);
            }

            // args.add("/usr/bin/service");
            // args.add("ssh");
            // args.add("start");
            env.add("PATH=/bin:/usr/bin:/home/tomek/bin");

            IGuestProcess process = gsession.processCreate(service
                    .getRunCommand(), args, env, flags, 0l);

            /*
             * IGuestProcess process = gsession.processCreate( "/home/tomek/bin/sshd-start", args, env, flags, 0l);
             */
            /*
             * System.out.println("guest process state:"); System.out.println(process.getStatus());
             * System.out.println("waiting for the proces to complete:");
             */
            // FIXME: move execution to another thread. wait as long as it takes. possibly use some kind of ThreadPool

            Thread.sleep(300);
            System.out.print("guest process state:");
            System.out.println(process.getStatus());
            System.out.println(process.getExitCode());
            /*
             * System.out.println(process.getStatus()); System.out.println(process.getExecutablePath());
             * System.out.println(process.getEnvironment()); System.out.println(process.getExitCode());
             */
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (gsession != null) {
                gsession.close();
            }
            session.unlockMachine();
        }

    }
}
