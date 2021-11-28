package Server.Controller;

import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.Message;

public class MoveController extends BaseNetworkController {
	private void move(Client client, int move) {
		Message<Integer> message = new Message<>(move, Message.Action.MOVE);
		this.getNetworkClient().send(client.getGame().getOpponent(), message);
	}

	@Override
	public boolean supports(ClientMessage clientMessage) {
		return Message.Action.MOVE == clientMessage.getMessage().getAction();

	}

	@Override
	public void resolve(ClientMessage clientMessage) {
		this.move(clientMessage.getClient(), (int) clientMessage.getMessage().getObject());

	}
}
