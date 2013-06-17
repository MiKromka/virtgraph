package pl.edu.agh.iosr.virtgraph.dao;

import org.neo4j.graphdb.RelationshipType;

public class ContainsRelationshipType implements RelationshipType {
	private static ContainsRelationshipType instance = new ContainsRelationshipType();

	@Override
	public String name() {
		return RelationTypes.CONTAINS;
	}

	public static RelationshipType getInstance() {
		return instance;
	}

}
