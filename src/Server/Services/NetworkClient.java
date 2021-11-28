package Server.Services;


import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.Message;

import java.io.IOException;
import java.net.Socket;
/**
 * Creates the instance of networkClient which communicates with server.
 * @author Other-6.4
 *
 */
public class NetworkClient {
	/**
	 * Sends the message to the server
	 * @param client current client joining server
	 * @param message client's message to server
	 */
    public void send(Client client, Message message) {
        try {
            client.getObjectOutputStream().writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();

            return;
        }

    }
}
