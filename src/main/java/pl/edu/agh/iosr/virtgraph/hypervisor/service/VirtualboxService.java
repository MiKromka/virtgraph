package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

import pl.edu.agh.iosr.virtgraph.hypervisor.communicator.ServerCommunicator;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;
import pl.edu.agh.iosr.virtgraph.properties.Properties;

@Service
public class VirtualboxService implements VirtualMachineService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(VirtualboxService.class);

    private VirtualBoxManager vBoxManager;
    private IVirtualBox vBox;

    @Autowired
    ServerCommunicator serverCommunicator;

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

    @Override
    public void start(VirtualMachine vm) {
        // TODO implement me
        LOGGER.debug("Starting a virtualmachine(not implemented yet) :" + vm);
    }

    @Override
    public void startService(pl.edu.agh.iosr.virtgraph.model.Service service) {

        // TODO finish me, check me, use the sent service
        IMachine machine = vBox.getMachines().get(4); // read-only machne
        ISession session = null;
        IGuestSession gsession = null;
        try {
            session = vBoxManager.getSessionObject();

            System.out.println("session state:");
            System.out.println(session.getState());

            System.out.println("locking the machine...");
            machine.lockMachine(session, LockType.Shared);

            System.out.println("session state:");
            System.out.println(session.getState());

            String user = Properties.getVirtualMachineUser();
            String pass = Properties.getVirtualMachinePassword();

            System.out.println("creatind guest session...");
            IConsole console = session.getConsole();
            IGuest guest = console.getGuest();
            // arguments 3 and 4 are opional

            gsession = guest.createSession("root", pass, "", "");

            System.out.println("guest session state:");

            System.out.println(gsession.getDomain());
            System.out.println(gsession.getName());
            System.out.println(gsession.getUser());

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
            System.out.println("guest process state:");
            System.out.println(process.getStatus());
            System.out.println("waiting for the proces to complete:");
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

    @Override
    public void stopService(pl.edu.agh.iosr.virtgraph.model.Service service) {
        // TODO Auto-generated method stub

    }

}
