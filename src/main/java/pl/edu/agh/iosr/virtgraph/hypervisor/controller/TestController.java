package pl.edu.agh.iosr.virtgraph.hypervisor.controller;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Controller
@Path("/test")
public class TestController {

    @Autowired
    StateProvider stateProvider;

    @GET
    @Path("/sampleservice")
    @Produces("application/xml")
    public Service sampleService(@PathParam("id") int id) {
        LinkedList<Service> list = new LinkedList<Service>();
        LinkedList<String> args = new LinkedList<String>();
        args.add("start");
        args.add("sshd");
        return new Service("sshd", "/usr/bin/systemctl", "/usr/bin/systemctl",
                true, args);
    }

    @GET
    @Path("/vm")
    @Produces("application/xml")
    public VirtualMachine sampleVm() {
        return new VirtualMachine("samplename", "sampleid", true);

    }

    @GET
    @Path("/hostlist")
    @Produces("application/xml")
    public HostList getHosts() {
        LinkedList<Host> list = new LinkedList<Host>();
        list.add(new Host("tomek-pc",
                "http://localhost:8080/virtgraph/hypervisor/"));
        HostList hlist = new HostList();
        hlist.setHosts(list);
        return hlist;
    }

    @GET
    @Path("/vmlist")
    @Produces("application/xml")
    public VMList getVMList() {
        return new VMList(stateProvider.getVMs());
    }

    @GET
    @Path("/servicelist")
    @Produces("application/xml")
    public ServiceList getServiceList() {
        LinkedList<Service> list = new LinkedList<Service>();
        LinkedList<String> args = new LinkedList<String>();
        args.add("start");
        args.add("sshd");
        list.add(new Service("sshd", "/usr/bin/systemctl",
                "/usr/bin/systemctl", true, args));
        return new ServiceList(list);
    }

}
