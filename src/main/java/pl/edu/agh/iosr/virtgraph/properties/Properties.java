package pl.edu.agh.iosr.virtgraph.properties;

public class Properties {
	private final static String SERVER_ADDRESS = "http://192.168.0.12:8080/virtgraph/dummy/";
	private final static String RELATIVE_SERVER_LOCATION = "/virtgraph/dummy/host";
	private final static boolean REGISTER_ON_STARTUP = false;

	public static boolean isRegisterOnStartup() {
		return REGISTER_ON_STARTUP;
	}

	public static String getServerAddress() {
		return SERVER_ADDRESS;
	}

	public static String getRelativeServerLocation() {
		return RELATIVE_SERVER_LOCATION;
	}
}
