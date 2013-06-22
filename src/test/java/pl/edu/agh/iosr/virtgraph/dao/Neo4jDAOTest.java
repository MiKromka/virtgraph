package pl.edu.agh.iosr.virtgraph.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class Neo4jDAOTest {
	@Test
	public void shouldCreateDaoWithIndexes() {
		// given
		Neo4jDAO dao = new Neo4jDAO();

		// when-then
		assertNotNull(dao.hosts);
		assertNotNull(dao.services);
		assertNotNull(dao.virtualMachines);
	}

}
