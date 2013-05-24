package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VirtualMachine {
    private String name;

    public VirtualMachine() {
    }

    public VirtualMachine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
