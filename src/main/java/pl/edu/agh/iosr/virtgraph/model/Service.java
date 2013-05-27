package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Service {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Service(String name) {
		this.name = name;
	}

	public Service() {
	}

}
