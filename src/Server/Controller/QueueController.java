package Server.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Server.Services.NetworkClient;
import application.BoardApplication;
import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.Field;
import utils.NetworkProvider.Model.Game;
import utils.NetworkProvider.Model.Message;

public class QueueController extends BaseNetworkController {
	ArrayList<Client> list = new ArrayList<>();

	private void queue(Client client) {
		NetworkClient networkClient = this.getNetworkClient();
		list.add(client);
		if (list.size() == 1) {
			Client current= list.get(0);
			Client opponent= list.get(0);
			BoardApplication board = new BoardApplication();
			board.fillBoard();
			
//			Game gameOpponent= new Game(opponent, current, board.getMap());
			Game gameCurrent= new Game(current, opponent, board.getMap());
			current.setGame(gameCurrent);
//			opponent.setGame(gameOpponent);
			
			Message<HashMap<Field,Color>> messageCurrent = new Message<>(board.getMap(), Message.Action.NEWGAME);
//			Message<Game> messageOpponent = new Message<>(gameOpponent, Message.Action.NEWGAME);
			getNetworkClient().send(current, messageCurrent);
//			getNetworkClient().send(opponent, messageOpponent);
			list.clear();
		}
	}

	@Override
	public boolean supports(ClientMessage clientMessage) {
		return Message.Action.QUEUE == clientMessage.getMessage().getAction();
	}

	@Override
	public void resolve(ClientMessage clientMessage) {
		this.queue(clientMessage.getClient());
	}
}
