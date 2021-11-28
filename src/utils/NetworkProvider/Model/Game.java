package utils.NetworkProvider.Model;

import java.io.Serializable;
import java.util.HashMap;


public class Game implements Serializable{
	private Client player;
	private Client opponent;
	private HashMap<Field, Color> hashMap;

	public Game(Client player, Client opponent, HashMap<Field, Color> hashMap) {
		this.player = player;
		this.opponent = opponent;
		this.hashMap = hashMap;
	}

	public Client getPlayer() {
		return player;
	}

	public void setPlayer(Client player) {
		this.player = player;
	}

	public HashMap<Field, Color> getBoard() {
		return hashMap;
	}

	public Client getOpponent() {
		return opponent;
	}

	public void setOpponent(Client opponent) {
		this.opponent = opponent;
	}

}
