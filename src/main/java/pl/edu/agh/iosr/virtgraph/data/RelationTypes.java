package pl.edu.agh.iosr.virtgraph.data;

import org.neo4j.graphdb.RelationshipType;

public enum RelationTypes implements RelationshipType {
	RUNS, CONTAINS, EXPOSES
}
