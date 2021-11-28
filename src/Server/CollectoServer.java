package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Server.Controller.HelloController;
import Server.Controller.ListController;
import Server.Controller.LoginController;
import Server.Controller.QueueController;
import Server.Services.Controller.Resolver;
import Server.Thread.ClientPullListenerThread;
import Server.Thread.MessageHandlerThread;
import Server.Thread.MessageListenerThread;

/**
 * CollectoServer 
 */
public class CollectoServer {
    public static void main(String[] args) {
    	ArrayList<Integer> ports= new ArrayList<>();
        Resolver resolver = new Resolver();

        HelloController helloController = new HelloController();
        LoginController loginController = new LoginController();
        ListController listController = new ListController();
        QueueController queueController = new QueueController();

        resolver.addController(helloController);
        resolver.addController(loginController);
        resolver.addController(listController);
        resolver.addController(queueController);

        // init threads
        try {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Type in your port number");
        	int port = sc.nextInt();
        	if(ports.contains(port)) {
        		System.out.println("Try another port number");
        	}else {
        		ports.add(port);
        		sc.close();
        		ClientPullListenerThread clientPullListenerThread = ClientPullListenerThread.create(port);
        		MessageListenerThread messageListenerThread = new MessageListenerThread(clientPullListenerThread);
        		MessageHandlerThread messageHandlerThread = new MessageHandlerThread(messageListenerThread, resolver);
        		
        		new Thread(clientPullListenerThread).start();
        		new Thread(messageListenerThread).start();
        		new Thread(messageHandlerThread).start();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
