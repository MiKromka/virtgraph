package pl.edu.agh.iosr.virtgraph.dao.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

public interface VirtualMachineRepository extends
		GraphRepository<VirtualMachine>,
		RelationshipOperationsRepository<VirtualMachine> {

	VirtualMachine findByName(String name);
}
