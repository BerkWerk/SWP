package Action;

import Interfaces.IAction;
import Interfaces.ITrigger;

//command in commandPattern
public class ObserverUpdate implements IAction {

    ITrigger trigger;

    public ObserverUpdate(ITrigger newTrigger){

        trigger = newTrigger;
    }

    @Override
    public void execute() throws InterruptedException {
        trigger.update();
    }
}