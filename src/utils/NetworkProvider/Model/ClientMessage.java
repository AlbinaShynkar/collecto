package utils.NetworkProvider.Model;

/**
 * Class ClientMessage
 */
public class ClientMessage {
    private Client client;
    private Message message;

    /**
     * @param client
     * @param message
     */
    public ClientMessage(Client client, Message message) {
        this.client = client;
        this.message = message;
    }

    /**
     * @return Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @return Message
     */
    public Message getMessage() {
        return message;
    }
}
