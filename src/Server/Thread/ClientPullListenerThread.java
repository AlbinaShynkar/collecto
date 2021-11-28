package Server.Thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class ServerListener
 */
public class ClientPullListenerThread implements Runnable {
    private ServerSocket serverSocket;
    private List<Socket> clients;

    /**
     * @param serverSocket
     */
    private ClientPullListenerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clients = new CopyOnWriteArrayList<Socket>();
    }

    /**
     * @param port 
     * @return ServerListener
     * @throws IOException
     */
    public static ClientPullListenerThread create(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        return new ClientPullListenerThread(serverSocket);
    }

    @Override
    public void run() {
        while (true) {
            try {
            	System.out.println("Waiting for clients...");
                Socket client = serverSocket.accept();
                System.out.println("User Connected!");

                clients.add(client);
            } catch (IOException  e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * @return List<Socket>
     */
    public List<Socket> getClients() {
        return clients;
    }
}
