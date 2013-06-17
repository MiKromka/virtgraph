package pl.edu.agh.iosr.virtgraph.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceList {
    private List<Service> services = new ArrayList<Service>();

    public ServiceList(List<Service> services) {
        this.services = services;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceList() {
        //
    }
}
