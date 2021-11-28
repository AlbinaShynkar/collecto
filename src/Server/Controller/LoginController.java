package Server.Controller;

import java.util.ArrayList;

import Server.Services.NetworkClient;
import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.Message;

public class LoginController extends BaseNetworkController {
	ArrayList<String> list = new ArrayList<>();

	private void login(Client client, String username) {

		System.out.println("LOGIN");

		NetworkClient networkClient = this.getNetworkClient();

		if (!list.contains(username)) {
			Message<String> message = new Message<>(username, Message.Action.LOGIN);
			networkClient.send(client, message);
			client.setUsername(username);
			list.add(username);
		} else {
			Message<String> error = new Message<>(username, Message.Action.ALREADYLOGGEDIN);
			networkClient.send(client, error);
		}

	}

	private void list(Client client) {
		Message<ArrayList<String>> messageList = new Message<>(this.list, Message.Action.LIST);
		this.getNetworkClient().send(client, messageList);

	}

	@Override
	public boolean supports(ClientMessage clientMessage) {
		return Message.Action.LOGIN == clientMessage.getMessage().getAction()
				|| Message.Action.LIST == clientMessage.getMessage().getAction();
	}

	@Override
	public void resolve(ClientMessage clientMessage) {
		if (clientMessage.getMessage().getAction().equals(Message.Action.LOGIN)) {
			this.login(clientMessage.getClient(), (String) clientMessage.getMessage().getObject());
		} else if (clientMessage.getMessage().getAction().equals(Message.Action.LIST)) {
			this.list(clientMessage.getClient());

		}
	}
}
