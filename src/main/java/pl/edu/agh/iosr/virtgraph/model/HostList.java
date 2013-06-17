package pl.edu.agh.iosr.virtgraph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import pl.edu.agh.iosr.virtgraph.dao.RelationTypes;

@NodeEntity
@XmlRootElement
public class HostList {

	@RelatedTo(type = RelationTypes.CONTAINS, direction = Direction.OUTGOING)
	private List<Host> hosts = new ArrayList<Host>();

	public List<Host> getHosts() {
		return hosts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hosts == null) ? 0 : hosts.hashCode());
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
		HostList other = (HostList) obj;
		if (hosts == null) {
			if (other.hosts != null) {
				return false;
			}
		} else if (!hosts.equals(other.hosts)) {
			return false;
		}
		return true;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}

	public HostList setHosts(Collection<Host> hosts) {
		this.hosts = new ArrayList<Host>(hosts);
		return this;
	}

	public void addHost(Host host) {
		if (hosts == null) {
			hosts = new ArrayList<Host>();
		}
		hosts.add(host);
	}

	public HostList() {
		//
	}
}
