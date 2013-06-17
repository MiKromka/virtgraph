package pl.edu.agh.iosr.virtgraph.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class Host implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GraphId
	Long id;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "hostSearch")
	private String name;

	private String address;

	public String getName() {
		return name;
	}

	@RelatedTo(type = RelationTypes.IS_CONTAINED,
			direction = Direction.INCOMING)
	private HostList hostList;

	public void setHostList(HostList hostList) {
		this.hostList = hostList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((hostList == null) ? 0 : hostList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Host other = (Host) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (hostList == null) {
			if (other.hostList != null) {
				return false;
			}
		} else if (!hostList.equals(other.hostList)) {
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
		if (vms == null) {
			if (other.vms != null) {
				return false;
			}
		} else if (!vms.equals(other.vms)) {
			return false;
		}
		return true;
	}

	public HostList getHostList() {
		return hostList;
	}

	@Fetch
	@RelatedTo(type = RelationTypes.RUNS, direction = Direction.OUTGOING)
	private VMList vms;

	public VMList vms() {
		return vms;
	}

	public void vm(VirtualMachine virtualMachine) {
		vms.addVm(virtualMachine);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Host(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Host() {
	}

}
