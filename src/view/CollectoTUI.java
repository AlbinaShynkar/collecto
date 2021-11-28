package view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.Scanner;

import Client.Controller.HelloController;
import Client.Services.NetworkClient;
import Client.Services.NetworkReceiver;
import Client.Services.Controller.Resolver;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Message;
import utils.NetworkProvider.Model.Player;
/**
 * The main class where the server client communication takes place, 
 * here the game is started, the board is printed etc.
 * @author User
 *
 */
public class CollectoTUI {
	protected static PrintStream printer = System.out;
	private static Scanner scannerIn = new Scanner(System.in);
	public static void main(String[] args) {
		//Player player = new Player();
		//Game game = new Game(player);
		NetworkClient networkClient;
		NetworkReceiver networkReceiver;
		Resolver resolver = new Resolver();
		ConnectionConfig connectionConfig = new ConnectionConfig(false, false, false);

		try {
			Player player = new Player(askName(), askAddress(), askPort());
			networkClient = player.getNetworkClient();
			HelloController helloController = new HelloController(networkClient);
			resolver.addController(helloController);
			networkReceiver = new NetworkReceiver(networkClient.getSocket(), resolver);
			Thread thread = new Thread(networkReceiver);
			thread.start();
			
			Message<ConnectionConfig> messageHello = 
					new Message<ConnectionConfig>(connectionConfig, Message.Action.HELLO);
			networkClient.send(messageHello);
//			Message<String> messageLogin = 
//					new Message<String>(player.getUsername(), Message.Action.LOGIN);
//			networkClient.send(messageLogin);
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
