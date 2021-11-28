package utils.NetworkProvider.Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Client.Services.NetworkClient;

public class Player {
	/**
	 * Stores name of player joined the game.
	 */
	private String name;

	/**
	 * Stores the total number of balls player scored
	 */
	private int points;

	/**
	 * stores array list of balls of the player that he got adjacent.
	 */
	private ArrayList<Field> field = new ArrayList<Field>();

	private int port;
	private String address;

	NetworkClient networkClient;

//  --CONSTRUCTORS--
	/**
	 * Constructor of player with certain name and color of marbles, which player
	 * cannot choose for himself.
	 * 
	 * @requires color!=EMPTY
	 * @param name  name of the player joined the game
	 * @param color color of marble of player joined the game
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Player(String name, String address, int port) throws UnknownHostException, IOException {
		assert name != null;
		assert address != null;
		this.name = name;
		this.address = address;
		this.port = port;
		this.points = 0;

		this.networkClient = NetworkClient.create(name, this.port, this.address);
	}
	/**
	 * Constructor to be used by ComputerPlayer
	 * @param name player name
	 */
	public Player(String name) {
		assert name != null;
		this.name = name;
		
	}



	// --ORIGINAL METHODS--
	/**
	 * Increases points of player depending on move player did
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}
	public int increasePoints() {
		return points++;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Field> getField() {
		return field;
	}

	public NetworkClient getNetworkClient() {
		return networkClient;
	}

	public void setNetworkClient(NetworkClient networkClient) {
		this.networkClient = networkClient;
	}

}
