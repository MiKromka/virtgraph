package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VirtualMachine {
    private String name;
    private String id;
    private boolean running;

    public VirtualMachine() {
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VirtualMachine(String name, String id, boolean running) {
        super();
        this.name = name;
        this.id = id;
        this.running = running;
    }

}
