package discussion.io;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import discussion.response.Connectable;
import discussion.response.Delegate;

/**
 * @author Elliott Bolzan
 *
 */
public class Receiver extends Actor implements Runnable {
	
	private String ID;
	private Collection<Delegate> delegates;
	private MulticastSocket socket;
	
	public Receiver(String ID, String host, int port, int bufferSize) {
		super(host, port, bufferSize);
		this.ID = ID;
		delegates = new ArrayList<Delegate>();
	}
	
	public void addReceiver(Connectable connectable, String key) {
		delegates.add(new Delegate(key, connectable));
	}

	@Override
	public void run() {
		System.setProperty("java.net.preferIPv4Stack", "true");
		try {
			byte[] buffer = new byte[getBufferSize()];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			setupSocket();
			while (true) {
				socket.receive(packet);
				Message message = getMessageFromBuffer(buffer);
				processMessage(message);
		        packet.setLength(buffer.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setupSocket() throws Exception {
		socket = new MulticastSocket(getPort());
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface iface = interfaces.nextElement();
			if (iface.isLoopback() || !iface.isUp()) {
				continue;
			}
			Enumeration<InetAddress> addresses = iface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress address = addresses.nextElement();
				socket.setInterface(address);
				socket.joinGroup(InetAddress.getByName(getHost()));
			}
		}
	}
	
	private Message getMessageFromBuffer(byte[] buffer) throws Exception {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer);
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
		Message message = (Message) is.readObject();
		is.close();
		return message;
	}
	
	private void processMessage(Message message) {
		for (Delegate delegate : delegates) {
			if (message.getKey().equals(delegate.getKey()) && !message.getSenderID().equals(ID)) {
				delegate.getConnectable().received(message.getObject());
				return;
			}
		}
	}

}
