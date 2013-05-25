package pl.edu.agh.iosr.virtgraph.hypervisor.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.hypervisor.service.VirtualMachineService;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Controller
@Path("/vm")
public class VMController {

    @Autowired
    VirtualMachineService vmService;

    @POST
    @Path("/start")
    @Consumes("application/xml")
    @Produces("text/plain")
    public String startVm(VirtualMachine vm) {
        vmService.start(vm);
        return "not implemented yet";
    }

    @GET
    @Path("/getinfo/{id}")
    @Produces("application/xml")
    public VirtualMachine getInfo(@PathParam("id") int id) {
        return new VirtualMachine("testVirtualMachine");
    }

}
