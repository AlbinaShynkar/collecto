package Client.Controller;

import java.io.IOException;
import java.util.HashMap;

import Client.Services.NetworkClient;
import Client.Services.Controller.BaseController;
import application.BoardApplication;
import application.GameApplication;
import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.Field;
import utils.NetworkProvider.Model.Game;
import utils.NetworkProvider.Model.Message;
import utils.NetworkProvider.Model.Player;

/**
 * QueueController handles queue request from clients side.
 * 
 * @author Other-6.4
 *
 */
public class NewGameController extends BaseController {
	NetworkClient networkClient;
	GameApplication game;

	/**
	 * QueueController constructor.
	 * 
	 * @param networkClient client connecting to server
	 */
	public NewGameController(NetworkClient networkClient) {
		this.networkClient = networkClient;

	}

	/**
	 * Handles clients message after queue
	 */
	private void newGameAction(HashMap<Field, Color> map) {
		BoardApplication board = new BoardApplication(map);
		Player player = new Player(null);
		Player player1 = new Player(null);
		this.game = new GameApplication(player1, player, this.networkClient);
		try {
			game.start(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void moveAction(int input) {
		this.game.userInput(input, this.game.getBoard());
	}

	@Override
	public boolean supports(Message message) {

		return message.getAction() == Message.Action.NEWGAME || message.getAction() == Message.Action.MOVE;
	}

	@Override
	public void resolve(Message message) {
		switch (message.getAction()) {
		case NEWGAME:
			this.newGameAction((HashMap<Field, Color>) message.getObject());
		case MOVE:
			this.moveAction((int) message.getObject());
		}

	}
}
