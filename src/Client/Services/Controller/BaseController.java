package Client.Services.Controller;

import utils.NetworkProvider.Model.Message;

public abstract class BaseController {
    public abstract boolean supports(Message message);
    public abstract void resolve(Message message);
}
