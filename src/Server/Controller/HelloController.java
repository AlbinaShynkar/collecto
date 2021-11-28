package Server.Controller;

import Server.Services.NetworkClient;
import utils.NetworkProvider.Model.Client;
import utils.NetworkProvider.Model.ClientMessage;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Message;

public class HelloController extends BaseNetworkController {
    private void hello(Client client, ConnectionConfig connectionConfig) {
        System.out.println("HELLO");

        NetworkClient networkClient = this.getNetworkClient();
        ConnectionConfig connectionConfigObject = (ConnectionConfig) connectionConfig.clone();
        Message<ConnectionConfig> message = new Message<>(connectionConfigObject, Message.Action.HELLO);

        networkClient.send(client, message);
    }

    @Override
    public boolean supports(ClientMessage clientMessage) {
        return Message.Action.HELLO == clientMessage.getMessage().getAction();
    }

    @Override
    public void resolve(ClientMessage clientMessage) {
        this.hello(clientMessage.getClient(), (ConnectionConfig) clientMessage.getMessage().getObject());
    }

}
