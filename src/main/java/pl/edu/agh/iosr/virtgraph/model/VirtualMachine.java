package pl.edu.agh.iosr.virtgraph.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class VirtualMachine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;
	private String name;
	private String vmId;
	private boolean running;
	@GraphId
	Long id;

	public VirtualMachine() {
	}

	public String getName() {
		return name;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return vmId;
	}

	public void setId(String id) {
		this.vmId = id;
	}

	public VirtualMachine(String name, String id, boolean running) {
		super();
		this.name = name;
		this.vmId = id;
		this.running = running;
	}

	@RelatedTo(type = RelationTypes.IS_CONTAINED,
			direction = Direction.INCOMING)
	private VMList vMList;

	public void setVMList(VMList vMList) {
		this.vMList = vMList;
	}

	public VMList getVMList() {
		return vMList;
	}

	@RelatedTo(type = RelationTypes.EXPOSES, direction = Direction.OUTGOING)
	private ServiceList serviceList;

	public void setServiceList(ServiceList serviceList) {
		this.serviceList = serviceList;
	}

	public ServiceList getServiceList() {
		return serviceList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (running ? 1231 : 1237);
		result = prime * result
				+ ((serviceList == null) ? 0 : serviceList.hashCode());
		result = prime * result + ((vMList == null) ? 0 : vMList.hashCode());
		result = prime * result + ((vmId == null) ? 0 : vmId.hashCode());
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
		VirtualMachine other = (VirtualMachine) obj;
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
		if (running != other.running) {
			return false;
		}
		if (serviceList == null) {
			if (other.serviceList != null) {
				return false;
			}
		} else if (!serviceList.equals(other.serviceList)) {
			return false;
		}
		if (vMList == null) {
			if (other.vMList != null) {
				return false;
			}
		} else if (!vMList.equals(other.vMList)) {
			return false;
		}
		if (vmId == null) {
			if (other.vmId != null) {
				return false;
			}
		} else if (!vmId.equals(other.vmId)) {
			return false;
		}
		return true;
	}
}
