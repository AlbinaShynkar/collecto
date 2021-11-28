package utils.NetworkProvider.Model;

import java.io.IOException;
import java.net.UnknownHostException;

public class ComputerPlayer extends Player{
	private Strategy strategy;
	
	public ComputerPlayer(String name,String address, int port, Strategy strategy) throws UnknownHostException, IOException{
		super(name,address, port);
		this.strategy = strategy;
	}

	public ComputerPlayer(String name) {
		super(name);
	}

}
