package Action;

import Interfaces.IAction;
import Interfaces.ITrigger;

//command in commandPattern
public class LogAvgServerLoad implements IAction {

    ITrigger trigger;

    public LogAvgServerLoad(ITrigger newTrigger){

        trigger = newTrigger;
    }

    @Override
    public void execute() throws InterruptedException {
        trigger.logAvgServerLoad();
    }
}
