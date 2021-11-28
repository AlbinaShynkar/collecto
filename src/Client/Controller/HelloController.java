package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.Services.NetworkClient;
import Client.Services.Controller.BaseController;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Message;
import utils.NetworkProvider.Model.Player;

/**
 * HelloController class handles hello request from client side.
 * 
 * @author Other-6.4
 *
 */
public class HelloController extends BaseController {
	NetworkClient networkClient;
	Player player;

	/**HelloController constructor.
	 * 
	 * @param networkClient client connecting to server
	 */
	public HelloController(NetworkClient networkClient) {
		this.networkClient = networkClient;
	}

	private void helloAction(ConnectionConfig connectionConfig) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String username = reader.readLine();
			Message<String> message = new Message<>(username, Message.Action.LOGIN);
			networkClient.send(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean supports(Message message) {

		return message.getAction() == Message.Action.HELLO;
	}

	@Override
	public void resolve(Message message) {

		this.helloAction((ConnectionConfig) message.getObject());

	}

}
