package pl.edu.agh.iosr.virtgraph;

import static com.lordofthejars.nosqlunit.neo4j.ManagedNeoServer.Neo4jServerRuleBuilder.newManagedNeo4jServerRule;
import junit.framework.Assert;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.agh.iosr.virtgraph.dao.repositories.HostListRepository;
import pl.edu.agh.iosr.virtgraph.dao.repositories.HostRepository;
import pl.edu.agh.iosr.virtgraph.dao.repositories.ServiceListRepository;
import pl.edu.agh.iosr.virtgraph.dao.repositories.ServiceRepository;
import pl.edu.agh.iosr.virtgraph.dao.repositories.VMListRepository;
import pl.edu.agh.iosr.virtgraph.dao.repositories.VirtualMachineRepository;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.neo4j.ManagedNeoServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("application-context-embedded-neo4j.xml")
public class WhenInformationAboutAMemberIsRequired {

	@ClassRule
	public static ManagedNeoServer managedNeoServer = newManagedNeo4jServerRule()
			.neo4jPath(
					"/Users/alexsotobueno/Applications/neo4j-community-1.7.2")
			.build();

	@Autowired
	private HostListRepository hostListRepository;
	@Autowired
	private HostRepository hostRepository;
	@Autowired
	private ServiceListRepository serviceListRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private VMListRepository vMListRepository;
	@Autowired
	private VirtualMachineRepository virtualMachineRepository;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@UsingDataSet(locations = "test-dataset.xml",
			loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void test1() {

		Assert.assertTrue(true);
	}

}
