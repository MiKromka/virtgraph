package pl.edu.agh.iosr.virtgraph.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VMList {
    private Collection<VirtualMachine> vms;

    public Collection<VirtualMachine> getVms() {
        return vms;
    }

    public void setVms(Collection<VirtualMachine> vms) {
        this.vms = vms;
    }

    public VMList(Collection<VirtualMachine> vms) {
        this.vms = vms;
    }

    public VMList() {
        //
    }
}
