package pl.edu.agh.iosr.virtgraph.properties;

public class Properties {
	// private final static String SERVER_ADDRESS =
	// "http://localhost:8080/virtgraph/server/server/";
	private final static String SERVER_ADDRESS = "http://10.20.127.152:8080/virtgraph/server/server/";
	private final static String RELATIVE_SERVER_LOCATION = "/virtgraph/dummy/host";
	private final static String HOST_NAME = "devHostName";
	private final static boolean REGISTER_ON_STARTUP = false;
	private final static boolean CONNECT_TO_VIRTUALBOX_ON_STARTUP = false;
	private final static boolean ENABLE_SERVICE_REGISTRATION = false;
	private final static boolean ENABLE_VM_REGISTRATION = false;
	private final static String VIRTUAL_MACHINE_USER = "tomek";
	private final static String VIRTUAL_MACHINE_PASSWORD = "xxx";
	private final static String INNER_VIRTUAL_MACHINE_USER = "root";
	private final static String INNER_VIRTUAL_MACHINE_PASSWORD = "xxx";
	private final static String VIRTUALBOX_HYPERVISOR_ADDRESS = "http://localhost:18083";
	private final static String SELF_ADDRESS = "http://localhost:8080/virtgraph/hypervisor/";

	public static String getSelfAddress() {
		return SELF_ADDRESS;
	}

	public static String getInnerVirtualMachineUser() {
		return INNER_VIRTUAL_MACHINE_USER;
	}

	public static String getInnerVirtualMachinePassword() {
		return INNER_VIRTUAL_MACHINE_PASSWORD;
	}

	public static boolean isEnableVmRegistration() {
		return ENABLE_VM_REGISTRATION;
	}

	public static String getHostName() {
		return HOST_NAME;
	}

	public static boolean isEnableServiceRegistration() {
		return ENABLE_SERVICE_REGISTRATION;
	}

	public static boolean isRegisterOnStartup() {
		return REGISTER_ON_STARTUP;
	}

	public static boolean isConnectToVirtualboxOnStartup() {
		return CONNECT_TO_VIRTUALBOX_ON_STARTUP;
	}

	public static String getServerAddress() {
		return SERVER_ADDRESS;
	}

	public static String getVirtualMachineUser() {
		return VIRTUAL_MACHINE_USER;
	}

	public static String getVirtualMachinePassword() {
		return VIRTUAL_MACHINE_PASSWORD;
	}

	public static String getVirtualboxHypervisorAddress() {
		return VIRTUALBOX_HYPERVISOR_ADDRESS;
	}

	public static String getRelativeServerLocation() {
		return RELATIVE_SERVER_LOCATION;
	}
}
