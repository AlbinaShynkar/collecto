package Server.Services.Controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utils.NetworkProvider.Model.ClientMessage;

public class Resolver extends BaseController {
    private List<BaseController> controllerList;

    public Resolver() {
        this.controllerList = new ArrayList<>();
    }

    public void addController(BaseController baseController) {
        this.controllerList.add(baseController);
    }


    @Override
    public boolean supports(ClientMessage clientMessage) {
        for (Iterator<BaseController> iterator = this.controllerList.iterator(); iterator.hasNext();) {
            BaseController controller = iterator.next();

            if (controller.supports(clientMessage)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void resolve(ClientMessage clientMessage) {
        for (Iterator<BaseController> iterator = this.controllerList.iterator(); iterator.hasNext();) {
            BaseController controller = iterator.next();

            if (controller.supports(clientMessage)) {
                controller.resolve(clientMessage);
            }
        }
    }
}
