package Server.Controller;


import Server.Services.NetworkClient;
import Server.Services.Controller.BaseController;
import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Message;

public class ListController extends BaseNetworkController {

    private void list(Client client) {
    	System.out.println("LIST");
    	
        NetworkClient networkClient = this.getNetworkClient();
    }

	@Override
    public boolean supports(ClientMessage clientMessage) {
        return Message.Action.LIST == clientMessage.getMessage().getAction();
    }

    @Override
    public void resolve(ClientMessage clientMessage) {
        this.list(clientMessage.getClient());
    }
}
