package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParagonZaLas {
	private int ile;
	private String test;

	public ParagonZaLas() {
		//
	}

	public ParagonZaLas(int ile, String test) {
		this.ile = ile;
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public int getIle() {
		return ile;
	}

	public void setIle(int ile) {
		this.ile = ile;
	}

}
