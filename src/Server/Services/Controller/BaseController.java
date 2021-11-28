package Server.Services.Controller;

import utils.NetworkProvider.Model.ClientMessage;

public abstract class BaseController {
    public abstract boolean supports(ClientMessage clientMessage);
    public abstract void resolve(ClientMessage clientMessage);
}
