package pl.edu.agh.iosr.virtgraph.hypervisor.service;

import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface VirtualMachineService {
    public void toggle(VirtualMachine vm);

    public void toggleService(String vmId, Service service);
}
