package pl.edu.agh.iosr.virtgraph.dao;

import org.neo4j.graphdb.RelationshipType;

public class RunsRelationshipType implements RelationshipType {
	private static RunsRelationshipType instance = new RunsRelationshipType();

	@Override
	public String name() {
		return RelationTypes.RUNS;
	}

	public static RelationshipType getInstance() {
		return instance;
	}

}
