package Server.Services;



import java.util.ArrayList;
import java.util.List;

import utils.NetworkProvider.Model.Client;

/**
 * Class UsersInQueueService
 */
public class UsersInQueueService {
    private List<Client> clients;

    public UsersInQueueService() {
        this.clients = new ArrayList<Client>();
    }

    /**
     * @param client
     * @param username
     */
    public void login(Client client, String username) {
        client.setUsername(username);

        this.clients.add(client);
    }
}
