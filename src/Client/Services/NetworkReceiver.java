package Client.Services;


import utils.NetworkProvider.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Client.Services.Controller.Resolver;

public class NetworkReceiver implements Runnable {
    private Socket socket;
    private Resolver resolver;

    public NetworkReceiver(Socket socket, Resolver resolver) {
        this.socket = socket;
        this.resolver = resolver;
    }

    @Override
    public void run() {
        while (true) {
            ObjectInputStream inputStream;
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) inputStream.readObject();

                if (resolver.supports(message)) {
                    resolver.resolve(message);
                } else {
                    System.out.println("Unexpected server message.");
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

    }

}
