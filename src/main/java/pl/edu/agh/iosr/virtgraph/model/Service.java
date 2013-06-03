package pl.edu.agh.iosr.virtgraph.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Service {
	private String name;
	private String runCommand;
	private String stopCommand;
	private boolean start;
	private List<String> args;

	public Service(String name, String runCommand, String stopCommand,
			boolean start, List<String> args) {
		super();
		this.name = name;
		this.runCommand = runCommand;
		this.stopCommand = stopCommand;
		this.start = start;
		this.args = args;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public Service() {
	}

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

	public String getRunCommand() {
		return runCommand;
	}

	public void setRunCommand(String runCommand) {
		this.runCommand = runCommand;
	}

	public String getStopCommand() {
		return stopCommand;
	}

	public void setStopCommand(String stopCommand) {
		this.stopCommand = stopCommand;
	}

}
