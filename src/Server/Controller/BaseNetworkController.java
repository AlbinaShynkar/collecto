package Server.Controller;

import Server.Services.NetworkClient;
import Server.Services.Controller.BaseController;

public abstract class BaseNetworkController extends BaseController {
    private NetworkClient networkClient;

    protected NetworkClient getNetworkClient() {
        if (null == this.networkClient) {
            this.networkClient = new NetworkClient();
        }

        return networkClient;
    }
}
