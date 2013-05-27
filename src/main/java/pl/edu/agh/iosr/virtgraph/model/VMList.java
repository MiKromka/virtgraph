package pl.edu.agh.iosr.virtgraph.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VMList {
	private List<VirtualMachine> vms = new ArrayList<VirtualMachine>();

	public List<VirtualMachine> getVms() {
		return vms;
	}

	public void setVms(List<VirtualMachine> vms) {
		this.vms = vms;
	}

	public VMList() {
		//
	}
}
