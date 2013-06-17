package pl.edu.agh.iosr.virtgraph.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class Service {
	private String name;
	private String runCommand;
	private String stopCommand;
	private boolean start;
	private List<String> startArgs;
	private List<String> stopArgs;

	public List<String> getStartArgs() {
		return startArgs;
	}

	public void setStartArgs(List<String> startArgs) {
		this.startArgs = startArgs;
	}

	public List<String> getStopArgs() {
		return stopArgs;
	}

	public void setStopArgs(List<String> stopArgs) {
		this.stopArgs = stopArgs;
	}

	public List<String> getstartArgs() {
		return startArgs;
	}

	@GraphId
	Long id;

	public Service(String name, String runCommand, String stopCommand,
			boolean start, List<String> startArgs, List<String> stopArgs) {
		super();
		this.name = name;
		this.runCommand = runCommand;
		this.stopCommand = stopCommand;
		this.start = start;
		this.startArgs = startArgs;
		this.stopArgs = stopArgs;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((startArgs == null) ? 0 : startArgs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((runCommand == null) ? 0 : runCommand.hashCode());
		result = prime * result
				+ ((serviceList == null) ? 0 : serviceList.hashCode());
		result = prime * result + (start ? 1231 : 1237);
		result = prime * result
				+ ((stopCommand == null) ? 0 : stopCommand.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Service other = (Service) obj;
		if (startArgs == null) {
			if (other.startArgs != null) {
				return false;
			}
		} else if (!startArgs.equals(other.startArgs)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (runCommand == null) {
			if (other.runCommand != null) {
				return false;
			}
		} else if (!runCommand.equals(other.runCommand)) {
			return false;
		}
		if (serviceList == null) {
			if (other.serviceList != null) {
				return false;
			}
		} else if (!serviceList.equals(other.serviceList)) {
			return false;
		}
		if (start != other.start) {
			return false;
		}
		if (stopCommand == null) {
			if (other.stopCommand != null) {
				return false;
			}
		} else if (!stopCommand.equals(other.stopCommand)) {
			return false;
		}
		return true;
	}

	@RelatedTo(type = RelationTypes.IS_CONTAINED,
			direction = Direction.OUTGOING)
	private ServiceList serviceList;

	public void setServiceList(ServiceList serviceList) {
		this.serviceList = serviceList;
	}

	public ServiceList getServiceList() {
		return serviceList;
	}

}
