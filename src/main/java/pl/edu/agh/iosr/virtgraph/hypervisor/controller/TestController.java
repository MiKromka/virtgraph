package pl.edu.agh.iosr.virtgraph.hypervisor.controller;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.hypervisor.state.StateProvider;
import pl.edu.agh.iosr.virtgraph.model.Service;
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
        List<String> args = new LinkedList<String>();
        args.add("/home/tomek/test");
        return new Service("create-test-file", "/usr/bin/touch", "/usr/bin/rm",
                true, args);
    }

    @GET
    @Path("/vm")
    @Produces("application/xml")
    public VirtualMachine sampleVm() {
        return new VirtualMachine("samplename", "sampleid", true);

    }

    @GET
    @Path("/vmlist")
    @Produces("application/xml")
    public VMList getVMList() {
        return new VMList(stateProvider.getVMs());
    }

}
