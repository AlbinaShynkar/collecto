package Server.Thread;

import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class MessageListener
 */
public class MessageListenerThread implements Runnable {
	private ClientPullListenerThread clientPullListenerThread;
	private List<ClientMessage> messages;

	/**
	 * @param clientPullListenerThread
	 */
	public MessageListenerThread(ClientPullListenerThread clientPullListenerThread) {
		this.clientPullListenerThread = clientPullListenerThread;
		this.messages = new CopyOnWriteArrayList<ClientMessage>();
	}

	@Override
	public void run()  {
		while (true) {
			System.out.print("----------------------------------------------");
			System.out.println(clientPullListenerThread.getClients().size());
			
			for (Socket client : clientPullListenerThread.getClients()) {
				
				try {
					ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());

					Message message = (Message) objectInputStream.readObject();

					System.out.println("Received Message...");

					Client userClient = new Client(client);

					this.messages.add(new ClientMessage(userClient, message));
				} catch (IOException e) {
					System.out.println("ERROR: " + e.toString());
					e.printStackTrace();
					return;
				} catch (ClassNotFoundException e) {
					System.out.println("ERROR: " + e.toString());
					e.printStackTrace();
					return;
				}
			}

			try {
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				System.out.println("ERROR: " + e.toString());
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * @return ClientMessage|null
	 */
	public ClientMessage getMessage() {
		try {
			ClientMessage message = this.messages.get(this.messages.size() - 1);
			this.messages.remove(this.messages.size() - 1);
			return message;
		} catch (IndexOutOfBoundsException exception) {
			return null;
		}
	}
}
