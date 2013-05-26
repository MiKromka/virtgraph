package pl.edu.agh.iosr.virtgraph.properties;

public class Properties {
	private final static String SERVER_ADDRESS = "http://localhost:8080/virtgraph/dummy/server";
	private final static String RELATIVE_SERVER_LOCATION = "/virtgraph/dummy/host";

	public static String getServerAddress() {
		return SERVER_ADDRESS;
	}
	
	public static String getRelativeServerLocation()
	{
		return RELATIVE_SERVER_LOCATION;
	}
}
