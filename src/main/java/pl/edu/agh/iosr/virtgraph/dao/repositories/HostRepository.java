package pl.edu.agh.iosr.virtgraph.dao.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import pl.edu.agh.iosr.virtgraph.model.Host;

public interface HostRepository extends GraphRepository<Host>,
		RelationshipOperationsRepository<Host> {

	Host findByName(String name);
}
