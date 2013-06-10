package pl.edu.agh.iosr.virtgraph.hypervisor.state;

import java.util.Collection;
import java.util.List;

import pl.edu.agh.iosr.virtgraph.hypervisor.exception.NotRegisteredException;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface StateProvider {

    public String getHostAddress() throws NotRegisteredException;

    public List<Service> getServices();

    public Collection<VirtualMachine> getVMs();

    public VirtualMachine getVM(String id);

    public void updateService(Service service);

    // public void updateVM(VirtualMachine vm);

    public void updateState();
}
