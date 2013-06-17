package pl.edu.agh.iosr.virtgraph;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.iosr.virtgraph.dao.Neo4jDAO;
import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;

public class DbRunnerTest {

	private final Neo4jDAO dao = new Neo4jDAO();

	@Test
	public void shouldSaveAndRestoreHost() {
		// given
		String name = "hostName4";
		Host host = new Host(name, "addr1");

		// when
		dao.addHost(name, host);
		HostList list = dao.getHostList();

		// then
		Assert.assertTrue("HostList must contain inserted host: "
				+ list.getHosts().size(), list.getHosts().contains(host));
	}
}
