package discussion;
import java.io.Serializable;
import java.util.UUID;

import discussion.io.*;
import discussion.response.*;

/**
 * @author Elliott Bolzan
 *
 */
public class Discussion {
	
	private static final String HOST = "224.0.0.1";
	private static final int PORT = 4456;
	private static final int BUFFER_SIZE = 5000;
	
	private Sender sender;
	private Receiver receiver;
	private String ID;

	public Discussion() {
		ID = UUID.randomUUID().toString();
        startReceiver();
		sender = new Sender(HOST, PORT, BUFFER_SIZE);
	}
	
	private void startReceiver() {
        receiver = new Receiver(ID, HOST, PORT, BUFFER_SIZE);
        Thread t = new Thread(receiver);
        t.start();
	}

	public void send(Serializable object, String key) {
	    Message message = new Message(ID, key, object);
		sender.send(message);
	}
	
	public void listenOnChannel(Connectable connectable, String key) {
		receiver.addReceiver(connectable, key);
	}

}
