package pl.edu.agh.iosr.virtgraph.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class ServiceList {
	@GraphId
	Long id;

	@RelatedTo(type = RelationTypes.CONTAINS, direction = Direction.OUTGOING)
	private List<Service> services = new ArrayList<Service>();

	public ServiceList(List<Service> services) {
		this.services = services;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public void addService(Service service) {
		if (services == null) {
			services = new ArrayList<Service>();
		}
		services.add(service);
	}

	@RelatedTo(type = RelationTypes.IS_EXPOSED, direction = Direction.INCOMING)
	private VirtualMachine virtualMachine;

	public void setVirtualMachine(VirtualMachine virtualMachine) {
		this.virtualMachine = virtualMachine;
	}

	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((services == null) ? 0 : services.hashCode());
		result = prime * result
				+ ((virtualMachine == null) ? 0 : virtualMachine.hashCode());
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
		ServiceList other = (ServiceList) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (services == null) {
			if (other.services != null) {
				return false;
			}
		} else if (!services.equals(other.services)) {
			return false;
		}
		if (virtualMachine == null) {
			if (other.virtualMachine != null) {
				return false;
			}
		} else if (!virtualMachine.equals(other.virtualMachine)) {
			return false;
		}
		return true;
	}

	public ServiceList() {
		//
	}
}
