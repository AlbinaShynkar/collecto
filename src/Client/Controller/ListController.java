package Client.Controller;

import java.util.ArrayList;

import Client.Services.NetworkClient;
import Client.Services.Controller.BaseController;
import utils.NetworkProvider.Model.Message;

/**
 * ListController handles list request from client side.
 * 
 * @author Other-6.4
 *
 */
public class ListController extends BaseController {
	NetworkClient networkClient;

	/**
	 * ListController constructor.
	 * 
	 * @param networkClient client connecting to server
	 */
	public ListController(NetworkClient networkClient) {
		this.networkClient = networkClient;
	}

	/**
	 * Prints all usernames joined server.
	 * 
	 * @param arrayList list of usernames on server
	 */
	private void list(ArrayList<String> arrayList) {
		for (String username : arrayList) {
			System.out.println(username);
		}
	}

	@Override
	public boolean supports(Message message) {
		return message.getAction() == Message.Action.LIST;
	}

	@Override
	public void resolve(Message message) {
		this.list((ArrayList<String>) message.getObject());

	}

}
