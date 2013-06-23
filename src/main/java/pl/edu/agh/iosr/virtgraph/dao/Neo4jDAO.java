package pl.edu.agh.iosr.virtgraph.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
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
import pl.edu.agh.iosr.virtgraph.model.Service;
import pl.edu.agh.iosr.virtgraph.model.ServiceList;
import pl.edu.agh.iosr.virtgraph.model.VMList;
import pl.edu.agh.iosr.virtgraph.model.VirtualMachine;

@Repository
public class Neo4jDAO implements DataAccessObject {

	private static final String DB_PATH = "D:\\dev\\lib\\neo4j-community-1.8.2";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Neo4jDAO.class);
	private static final String SERVICE_PROPERTY_NAME = "serviceName";
	private static final String VM_PROPERTY_NAME = "vmName";
	private static final String NODE_PROPERTY_NAME = "nodeName";
	private static final String OBJECT_PROPERTY = "nodeObject";

	private GraphDatabaseService grapDatabaseService;
	private IndexManager indexManager;
	public Index<Node> hosts;
	public Index<Node> virtualMachines;
	public Index<Node> services;
	RelationshipIndex nodeVmRelations;
	RelationshipIndex vMServiceRelations;

	public Neo4jDAO() {
		createDatabase();
	}

	public IndexHits<Node> queryHosts(String query) {
		LOGGER.debug("Query: {}", query);
		return hosts.query(query);
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

	public Node addService(String hostName, String vmId, Service service) {
		Transaction tx = grapDatabaseService.beginTx();
		try {
			Iterable<Node> nodes = queryHosts("*:*");
			Iterator<Node> it = nodes.iterator();
			Node host = null;
			while (it.hasNext()) {
				host = it.next();
				if (host.getProperty(NODE_PROPERTY_NAME, null).equals(hostName)) {
					host = null;
					break;
				}
			}
			if (host == null) {
				LOGGER.error("Couldn't find a host: {}!", hostName);
				return null;
			}

			VirtualMachine vm = null;
			for (Relationship r : host.getRelationships(
					RunsRelationshipType.getInstance(), Direction.OUTGOING)) {
				if (((VirtualMachine) SerializationUtils
						.deserialize(((byte[]) r.getEndNode().getProperty(
								OBJECT_PROPERTY, null)))).getId().equals(vmId)) {
					vm = (VirtualMachine) r.getEndNode();
					break;
				}
			}
			if (vm == null) {
				LOGGER.error("Couldn't find a vm: {}!", vmId);
				return null;
			}

			Node s = grapDatabaseService.createNode();
			s.setProperty(SERVICE_PROPERTY_NAME, service.getName());
			s.setProperty(OBJECT_PROPERTY,
					SerializationUtils.serialize(service));
			services.add(s, SERVICE_PROPERTY_NAME,
					s.getProperty(SERVICE_PROPERTY_NAME, null));
			host.createRelationshipTo(s, ExposesRelationshipType.getInstance());
			return s;
		} finally {
			tx.finish();
		}
	}

	public Node addVM(String id, String hostName, VirtualMachine virtualMachine) {
		Transaction tx = grapDatabaseService.beginTx();
		try {
			Iterable<Node> nodes = queryHosts("*:*");
			Iterator<Node> it = nodes.iterator();
			Node host = null;
			while (it.hasNext()) {
				host = it.next();
				if (host.getProperty(NODE_PROPERTY_NAME, null).equals(hostName)) {
					host = null;
					break;
				}
			}
			if (host == null) {
				LOGGER.error("Couldn't find a host: {}!", hostName);
				return null;
			}
			LOGGER.info("Adding VM: {}", id);
			Node vm = grapDatabaseService.createNode();
			vm.setProperty(VM_PROPERTY_NAME, id);
			vm.setProperty(OBJECT_PROPERTY,
					SerializationUtils.serialize(virtualMachine));
			virtualMachines.add(vm, VM_PROPERTY_NAME,
					vm.getProperty(VM_PROPERTY_NAME, null));
			host.createRelationshipTo(vm, RunsRelationshipType.getInstance());
			return vm;
		} finally {
			tx.finish();
		}

	}

	public Node addHost(String name, Host host) {
		Transaction tx = grapDatabaseService.beginTx();

		try {
			LOGGER.info("Adding node: {}", name);
			Node node = grapDatabaseService.createNode();
			node.setProperty(NODE_PROPERTY_NAME, name);

			node.setProperty(OBJECT_PROPERTY,
					SerializationUtils.serialize(host));
			hosts.add(node, NODE_PROPERTY_NAME, name);
			return node;
		} finally {
			tx.finish();
		}
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
		hosts = indexManager.forNodes("nodes");
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
	public String registerHost(Host host) {
		System.out.println("Adding host " + host.getName());
		addHost(host.getName(), host);
		return host.getName();
	}

	@Override
	public HostList getHostList() {
		System.out.println("Getting HostList");
		List<Host> list = new ArrayList<Host>();
		Iterable<Node> nodes = queryHosts("*:*");
		// hosts.get(NODE_PROPERTY_NAME, NODE_PROPERTY_NAME);
		// queryHosts("*:*");
		System.out.println("After query" + nodes);
		for (Node node : nodes) {
			System.out.println(node.getId());
			for (String propKey : node.getPropertyKeys()) {
				System.out.println(propKey);
			}
			Host host = (Host) SerializationUtils.deserialize(((byte[]) node
					.getProperty(OBJECT_PROPERTY, null)));
			list.add(host);
		}
		HostList hl = new HostList();
		hl.setHosts(list);
		return hl;
	}

	@Override
	public String registerService(String hostName, String vmId, Service service) {
		addService(hostName, vmId, service);
		return service.getName();
	}

	@Override
	public ServiceList getServiceList() {
		Iterable<Node> nodes = queryServices("*:*");
		ServiceList sl = new ServiceList();
		for (Node node : nodes) {
			Service host = (Service) SerializationUtils
					.deserialize(((byte[]) node.getProperty(OBJECT_PROPERTY,
							null)));
			sl.addService(host);
		}
		return sl;
	}

	@Override
	public String registerVMForHost(String hostName, VirtualMachine vm) {
		addVM(vm.getId(), hostName, vm);
		return vm.getId();
	}

	@Override
	public VMList getVMListForHost(String hostName) {
		Iterable<Node> nodes = queryHosts("*:*");
		Iterator<Node> it = nodes.iterator();
		Node host = null;
		while (it.hasNext()) {
			host = it.next();
			if (host.getProperty(NODE_PROPERTY_NAME, null).equals(hostName)) {
				host = null;
				break;
			}
		}
		if (host == null) {
			LOGGER.error("Couldn't find a host: {}!", hostName);
			return null;
		}

		VMList vmList = new VMList();
		for (Relationship r : host.getRelationships(
				RunsRelationshipType.getInstance(), Direction.OUTGOING)) {
			vmList.addVm((VirtualMachine) r.getEndNode());
		}
		return vmList;
	}

	@Override
	public ServiceList getServiceList(String hostName, String vmId) {
		Iterable<Node> nodes = queryHosts("*:*");
		Iterator<Node> it = nodes.iterator();
		Node host = null;
		while (it.hasNext()) {
			host = it.next();
			if (host.getProperty(NODE_PROPERTY_NAME, null).equals(hostName)) {
				host = null;
				break;
			}
		}
		if (host == null) {
			LOGGER.error("Couldn't find a host: {}!", hostName);
			return null;
		}

		Node vm = null;
		for (Relationship r : host.getRelationships(
				RunsRelationshipType.getInstance(), Direction.OUTGOING)) {
			if (((VirtualMachine) SerializationUtils.deserialize(((byte[]) r
					.getEndNode().getProperty(OBJECT_PROPERTY, null)))).getId()
					.equals(vmId)) {
				vm = r.getEndNode();
				break;
			}
		}
		if (vm == null) {
			LOGGER.error("Couldn't find a vm: {}!", vmId);
			return null;
		}
		ServiceList sl = new ServiceList();
		for (Relationship r : vm.getRelationships(
				ExposesRelationshipType.getInstance(), Direction.OUTGOING)) {
			sl.addService((Service) r.getEndNode());
		}
		return sl;
	}

	@Override
	public HostList getHostListWithService(Service service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VMList getVMListForHostWithService(Service service, String hostName) {
		// TODO Auto-generated method stub
		return null;
	}
}
