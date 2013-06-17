package pl.edu.agh.iosr.virtgraph.dao.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import pl.edu.agh.iosr.virtgraph.model.Service;

public interface ServiceRepository extends GraphRepository<Service>,
		RelationshipOperationsRepository<Service> {

	Service findByName(String name);
}
