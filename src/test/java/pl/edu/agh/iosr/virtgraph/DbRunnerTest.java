package pl.edu.agh.iosr.virtgraph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class DbRunnerTest {

    private GraphDatabaseService graphDb;

    @Before
    public void prepareTestDatabase() {
        this.graphDb = new TestGraphDatabaseFactory().newImpermanentDatabaseBuilder().newGraphDatabase();
    }

    @After
    public void destroyTestDatabase() {
        graphDb.shutdown();
    }

    @Test
    public void shouldCreateDbGetAndRemoveData() {

    }
}
