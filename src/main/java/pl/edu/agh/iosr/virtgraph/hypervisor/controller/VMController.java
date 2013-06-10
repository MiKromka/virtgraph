package pl.edu.agh.iosr.virtgraph.hypervisor.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.edu.agh.iosr.virtgraph.hypervisor.communicator.ServerCommunicator;
import pl.edu.agh.iosr.virtgraph.hypervisor.service.VirtualMachineService;
import pl.edu.agh.iosr.virtgraph.model.Service;

@Controller
@Path("/vms")
public class VMController {

    @Autowired
    VirtualMachineService vmService;

    // remove once done testing. used in sampleservice()
    @Autowired
    ServerCommunicator comm;

    /*
     * @POST
     * 
     * @Path("/")
     * 
     * @Consumes("application/xml")
     * 
     * @Produces("text/plain") public String startVm(VirtualMachine vm) { vmService.start(vm); return
     * "not implemented yet"; }
     */
    @POST
    @Path("/{vmid}/services")
    @Consumes("application/xml")
    public Response toggleService(@PathParam("vmid") String vmId,
            Service service) {
        if (service.isStart()) {
            vmService.startService(vmId, service);
        } else {
            // FIXME - not enough args
            vmService.stopService(service);
        }

        return Response.ok().build();
    }

    @PUT
    @Path("/{vmid}/services")
    @Consumes("application/xml")
    public String newService(@PathParam("vmid") int vmId, Service service) {
        return "not implemented yet";
    }

    @DELETE
    @Path("/{vmid}/services")
    @Consumes("application/xml")
    public String deleteService(@PathParam("vmid") int vmId, Service service) {
        return "not implemented yet";
    }

}
