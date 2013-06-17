package pl.edu.agh.iosr.virtgraph.dao;

import org.neo4j.graphdb.RelationshipType;

public class ExposesRelationshipType implements RelationshipType {
	private static ExposesRelationshipType instance = new ExposesRelationshipType();

	@Override
	public String name() {
		return RelationTypes.EXPOSES;
	}

	public static RelationshipType getInstance() {
		return instance;
	}

}
