package utils.NetworkProvider.Model;

import java.io.IOException;
import java.net.UnknownHostException;

public class ComputerPlayerSmart extends Player{
    private Strategy strategy;

    public ComputerPlayerSmart(String name,String address, int port, Strategy strategy) throws UnknownHostException, IOException{
        super(name,address, port);
        this.strategy = strategy;
    }

    //test
    public ComputerPlayerSmart(String name) {
        super(name);
    }
}