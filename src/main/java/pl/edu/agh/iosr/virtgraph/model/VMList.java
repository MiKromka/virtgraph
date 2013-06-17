package pl.edu.agh.iosr.virtgraph.model;

import java.util.Collection;
import java.util.HashSet;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class VMList {
	@GraphId
	Long id;

	@RelatedTo(type = RelationTypes.IS_RUN, direction = Direction.OUTGOING)
	private Host host;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((vms == null) ? 0 : vms.hashCode());
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
		VMList other = (VMList) obj;
		if (host == null) {
			if (other.host != null) {
				return false;
			}
		} else if (!host.equals(other.host)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (vms == null) {
			if (other.vms != null) {
				return false;
			}
		} else if (!vms.equals(other.vms)) {
			return false;
		}
		return true;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Host getHost() {
		return host;
	}

	@RelatedTo(type = RelationTypes.CONTAINS, direction = Direction.OUTGOING)
	private Collection<VirtualMachine> vms = new HashSet<VirtualMachine>();

	public Collection<VirtualMachine> getVms() {
		return vms;
	}

	public void setVms(Collection<VirtualMachine> vms) {
		this.vms = vms;
	}

	public VMList(Collection<VirtualMachine> vms) {
		this.vms = vms;
	}

	public void addVm(VirtualMachine virtualMachine) {
		if (vms == null) {
			vms = new HashSet<VirtualMachine>();
		}
		vms.add(virtualMachine);
	}

	public VMList() {
		//
	}
}
