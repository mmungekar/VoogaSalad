package discussion.io;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class Actor {

	private String host;
	private int port;
	private int bufferSize;
	
	public Actor(String host, int port, int bufferSize) {
		this.host = host;
		this.port = port;
		this.bufferSize = bufferSize;
	}

	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
	
}
