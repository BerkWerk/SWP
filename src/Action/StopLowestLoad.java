package Action;

import Interfaces.IAction;
import Interfaces.ITrigger;

//command in commandPattern
public class StopLowestLoad implements IAction {

    ITrigger trigger;

    public StopLowestLoad(ITrigger newTrigger){

        trigger = newTrigger;
    }

    @Override
    public void execute() throws InterruptedException {
        trigger.stopLowestLoad();
    }
}
