package Client;

import Client.Controller.HelloController;
import Client.Controller.ListController;
import Client.Controller.LoginController;
import Client.Controller.NewGameController;
import Client.Services.NetworkClient;
import Client.Services.NetworkReceiver;
import Client.Services.Controller.Resolver;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Message;
import utils.NetworkProvider.Model.Player;

import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CollectoClient {
	protected static PrintStream printer = System.out;
	private static Scanner scannerIn = new Scanner(System.in);
	public static void main(String[] args) {
		NetworkClient networkClient;
		NetworkReceiver networkReceiver;
		Resolver resolver = new Resolver();
		ConnectionConfig connectionConfig = new ConnectionConfig(false, false, false);

		try {
			Player player = new Player(askName(), askAddress(), askPort());
			networkClient = player.getNetworkClient();
			HelloController helloController = new HelloController(networkClient);
			resolver.addController(helloController);
			LoginController loginController= new LoginController(networkClient);
			resolver.addController(loginController);
			ListController listController= new ListController(networkClient);
			resolver.addController(listController);
			NewGameController queueController = new NewGameController(networkClient);
			resolver.addController(queueController);
			
			networkReceiver = new NetworkReceiver(networkClient.getSocket(), resolver);
			Thread thread = new Thread(networkReceiver);
			thread.start();
			
			Message<ConnectionConfig> message = new Message<>(connectionConfig, Message.Action.HELLO);
			networkClient.send(message);
			System.out.println(message);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Asks user to enter their name and returns it.
	 * 
	 * @return The name entered by the user as a String
	 */
	protected static String askName() {
		printer.print("Please enter your name: ");
		String name = scannerIn.nextLine();
		return name;
	}

	/**
	 * Asks user to enter the desired host and returns the entered String as an
	 * InetAddress.
	 * 
	 * @return The InetAddress specified by the user
	 * @throws UnknownHostException if host specified is invalid
	 */
	private static String askAddress() throws UnknownHostException {
		printer.print("What address (host) are you connecting to? ");
		String address = scannerIn.nextLine();
		return address;
	}

	/**
	 * Asks user the port to be used for the connection and returns the entered
	 * String as an integer.
	 * 
	 * @return The port specified by the user, as an integer
	 * @throws NumberFormatException If port is an invalid number
	 */
	private static int askPort() throws NumberFormatException {
		printer.print("What port are you connecting to? ");
		String portStr = scannerIn.nextLine();
		int portNo = Integer.parseInt(portStr);
		return portNo;
	}
}
