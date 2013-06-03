package pl.edu.agh.iosr.virtgraph.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@XmlRootElement
public class Host {
	@GraphId
	Long id;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "search")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Host(String name) {
		this.name = name;
	}

	public Host() {
	}

}
