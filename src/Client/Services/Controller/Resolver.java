package Client.Services.Controller;

import utils.NetworkProvider.Model.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Resolver extends BaseController {
    private List<BaseController> controllerList;

    public Resolver() {
        this.controllerList = new ArrayList<>();
    }

    public void addController(BaseController baseController) {
        this.controllerList.add(baseController);
    }


    @Override
    public boolean supports(Message message) {
        for (Iterator<BaseController> iterator = this.controllerList.iterator(); iterator.hasNext();) {
            BaseController controller = iterator.next();

            if (controller.supports(message)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void resolve(Message message) {
        for (Iterator<BaseController> iterator = this.controllerList.iterator(); iterator.hasNext();) {
            BaseController controller = iterator.next();

            if (controller.supports(message)) {
                controller.resolve(message);
            }
        }
    }
}
