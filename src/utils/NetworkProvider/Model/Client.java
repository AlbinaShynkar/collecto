package utils.NetworkProvider.Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Class Client
 */
public class Client implements Serializable{

	private Socket client;
    private ObjectOutputStream objectOutputStream;
    private String username;
    private Game game;

    /**
     * @param client
     */
    public Client(Socket client) {
        this.client = client;
    }

    /**
     * @return Socket
     */
    public Socket getClient() {
        return client;
    }

    public ObjectOutputStream getObjectOutputStream() throws IOException {
        if (this.objectOutputStream == null) {
            this.objectOutputStream = new ObjectOutputStream(this.client.getOutputStream());
        }

        return this.objectOutputStream;
    }
    
    public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
