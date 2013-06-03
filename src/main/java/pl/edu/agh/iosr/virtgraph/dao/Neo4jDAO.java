package pl.edu.agh.iosr.virtgraph.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.index.RelationshipIndex;
import org.neo4j.tooling.GlobalGraphOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pl.edu.agh.iosr.virtgraph.model.Host;
import pl.edu.agh.iosr.virtgraph.model.HostList;

@Repository
public class Neo4jDAO implements DataAccessObject {

	private static final String DB_PATH = "D:\\dev\\lib\\neo4j-community-1.8.2";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Neo4jDAO.class);
	private static final String SERVICE_PROPERTY_NAME = "serviceName";
	private static final String VM_PROPERTY_NAME = "vmName";
	private static final String NODE_PROPERTY_NAME = "nodeName";

	private GraphDatabaseService grapDatabaseService;
	private IndexManager indexManager;
	private Index<Node> computerNodes;
	private Index<Node> virtualMachines;
	private Index<Node> services;
	RelationshipIndex nodeVmRelations;
	RelationshipIndex vMServiceRelations;

	public IndexHits<Node> queryNodes(String query) {
		LOGGER.debug("Query: {}", query);
		return computerNodes.query(query);
	}

	public IndexHits<Node> queryVMs(String query) {
		LOGGER.debug("Query: {}", query);
		return virtualMachines.query(query);
	}

	public IndexHits<Node> queryServices(String query) {
		LOGGER.debug("Query: {}", query);
		return services.query(query);
	}

	public Iterable<Node> getAllNodes() {
		return GlobalGraphOperations.at(grapDatabaseService).getAllNodes();
	}

	public Node addService(String name) {
		LOGGER.info("Adding service: {}", name);
		Node service = grapDatabaseService.createNode();
		service.setProperty(SERVICE_PROPERTY_NAME, name);
		services.add(service, SERVICE_PROPERTY_NAME,
				service.getProperty(SERVICE_PROPERTY_NAME));
		return service;
	}

	public Node addVM(String name) {
		LOGGER.info("Adding VM: {}", name);
		Node vm = grapDatabaseService.createNode();
		vm.setProperty(VM_PROPERTY_NAME, name);
		services.add(vm, VM_PROPERTY_NAME, vm.getProperty(VM_PROPERTY_NAME));
		return vm;
	}

	public Node addNode(String name) {
		LOGGER.info("Adding node: {}", name);
		Node node = grapDatabaseService.createNode();
		node.setProperty(NODE_PROPERTY_NAME, name);
		services.add(node, NODE_PROPERTY_NAME,
				node.getProperty(NODE_PROPERTY_NAME));
		return node;
	}

	public Node getNodeWithProperty(String propertyName, String value) {
		for (Node node : GlobalGraphOperations.at(grapDatabaseService)
				.getAllNodes()) {
			if (value.equals(node.getProperty(propertyName, null))) {
				return node;
			}
		}

		return null;
	}

	public Node createNodeWithProperty(String propertyName, String value) {
		Node result = null;
		Transaction tx = grapDatabaseService.beginTx();

		try {
			result = grapDatabaseService.createNode();
			result.setProperty(propertyName, value);
			tx.success();
		} finally {
			tx.finish();
		}
		return result;
	}

	public void createDatabase() {
		LOGGER.info("Creating database");
		grapDatabaseService = new GraphDatabaseFactory()
				.newEmbeddedDatabase(DB_PATH);
		indexManager = grapDatabaseService.index();
		services = indexManager.forNodes("services");
		virtualMachines = indexManager.forNodes("VMs");
		computerNodes = indexManager.forNodes("nodes");
		vMServiceRelations = indexManager.forRelationships("vMService");
		nodeVmRelations = indexManager.forRelationships("nodeVm");
		LOGGER.info("Created database with node indexes: services, VMs, nodes; relationShopIndexes: node->VM and VM->Service");
	}

	public void removeData() {
		Transaction tx = grapDatabaseService.beginTx();
		try {
			for (Node node : GlobalGraphOperations.at(grapDatabaseService)
					.getAllNodes()) {
				node.delete();
			}
			tx.success();
		} finally {
			tx.finish();
		}

	}

	public void shutDown() {
		grapDatabaseService.shutdown();
	}

	@Override
	public int registerHost(Host host) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HostList getHostList() {
		// TODO Auto-generated method stub
		return null;
	}
}
