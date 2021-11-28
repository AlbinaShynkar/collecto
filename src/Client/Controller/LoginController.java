package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.Services.NetworkClient;
import Client.Services.Controller.BaseController;
import utils.NetworkProvider.Model.Message;

/**
 * LoginController handles login request from client side.
 * 
 * @author Other-6.4
 *
 */
public class LoginController extends BaseController {
	NetworkClient networkClient;

	/**
	 * Constructor of login controller
	 * 
	 * @param networkClient client connecting to server
	 */
	public LoginController(NetworkClient networkClient) {
		this.networkClient = networkClient;
	}

	/**
	 * Handles clients message after login.
	 * 
	 * @param message client's message
	 */
	private void loginAction(Message message) {
		System.out.println("LOGIN. Type LIST to see players on server or QUEUE to join queue for NEWGAME");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = reader.readLine();
			if (input.equals("LIST")) {
				Message<String> list = new Message<>(null, Message.Action.LIST);
				networkClient.send(list);

			} else if (input.equals("QUEUE")) {
				Message<String> queue = new Message<>(null, Message.Action.QUEUE);
				networkClient.send(queue);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Handles client's message if client input is the mane already in use.
	 * 
	 * @param message client's message
	 */
	private void alreadyLoggedIn(Message message) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("This username already in use.");
			String username = reader.readLine();
			Message<String> newMessage = new Message<>(username, Message.Action.LOGIN);
			networkClient.send(newMessage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean supports(Message message) {

		return message.getAction() == Message.Action.LOGIN || message.getAction() == Message.Action.ALREADYLOGGEDIN;
	}

	@Override
	public void resolve(Message message) {
		if (message.getAction() == Message.Action.LOGIN) {
			this.loginAction(message);
		} else if (message.getAction() == Message.Action.ALREADYLOGGEDIN) {
			this.alreadyLoggedIn(message);
		}

	}

}
