package pl.edu.agh.iosr.virtgraph.dao.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import pl.edu.agh.iosr.virtgraph.model.HostList;

public interface HostListRepository extends GraphRepository<HostList>,
		RelationshipOperationsRepository<HostList> {
}
