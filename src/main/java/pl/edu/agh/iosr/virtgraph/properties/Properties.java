package pl.edu.agh.iosr.virtgraph.properties;

public class Properties {
	private final static String SERVER_ADDRESS = "http://192.168.0.12:8080/virtgraph/dummy/";
	private final static String RELATIVE_SERVER_LOCATION = "/virtgraph/dummy/host";
	private final static boolean REGISTER_ON_STARTUP = false;
	private final static boolean CONNECT_TO_VIRTUALBOX_ON_STARTUP = true;
	private final static boolean ENABLE_SERVICE_REGISTRATION = true;
	private final static String VIRTUAL_MACHINE_USER = "tomek";
	private final static String VIRTUAL_MACHINE_PASSWORD = "wereti";
	private final static String VIRTUALBOX_HYPERVISOR_ADDRESS = "http://localhost:18083";

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
