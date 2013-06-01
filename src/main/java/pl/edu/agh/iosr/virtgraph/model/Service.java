package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Service {
	private String name;
	private boolean start;

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

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
