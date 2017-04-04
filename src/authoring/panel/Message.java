/**
 * 
 */
package authoring.panel;

import java.io.Serializable;

/**
 * @author Elliott Bolzan
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 296549524151151475L;
	private String username;
	private String message;
	
	/**
	 * 
	 */
	public Message(String username, String message) {
		this.username = username;
		this.message = message;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getMessage() {
		return message;
	}

}
