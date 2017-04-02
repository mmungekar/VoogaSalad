package discussion.response;

/**
 * @author Elliott Bolzan
 *
 */
public class Delegate {

	private String key;
	private Connectable connectable;
	
	public Delegate(String key, Connectable connectable) {
		this.key = key;
		this.connectable = connectable;
	}

	public String getKey() {
		return key;
	}

	public Connectable getConnectable() {
		return connectable;
	}

}
