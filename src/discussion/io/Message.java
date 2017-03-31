package discussion.io;
import java.io.Serializable;

/**
 * @author Elliott Bolzan
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String senderID;
	private String key;
	private Object object;

	public Message(String senderID, String key, Object object) {
		this.senderID = senderID;
		this.key = key;
		this.object = object;
	}

	public String getSenderID() {
		return senderID;
	}

	public String getKey() {
		return key;
	}

	public Object getObject() {
		return object;
	}

}
