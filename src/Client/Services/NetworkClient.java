package Client.Services;

import utils.NetworkProvider.Model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Network client creates new client on the server.
 * 
 * @author Other-6.4
 *
 */
public class NetworkClient {
	/**
	 * client's socket
	 */
	private Socket socket;

	/**
	 * NetworkClient constructor.
	 * 
	 * @param socket client's socket
	 */
	private NetworkClient(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Creates new client with new socket on server.
	 * 
	 * @return new client with new socket on server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static NetworkClient create(String name, int port, String address) throws UnknownHostException, IOException {
		Socket socket = new Socket(address, port);

		return new NetworkClient(socket);
	}

	/**
	 * Sends client's message to the output stream.
	 * 
	 * @param message message from client with some object
	 * @throws IOException
	 */
	public void send(Message<?> message) throws IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(message);
		outputStream.flush();
	}

	public Socket getSocket() {
		return socket;
	}

}