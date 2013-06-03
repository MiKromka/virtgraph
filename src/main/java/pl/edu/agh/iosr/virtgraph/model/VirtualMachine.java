package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VirtualMachine {
	private String name;
	private int id;

	public VirtualMachine() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VirtualMachine(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

}
