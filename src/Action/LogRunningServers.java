package Action;

import Interfaces.IAction;
import Interfaces.ITrigger;

//command in commandPattern
public class LogRunningServers implements IAction {

    ITrigger trigger;

    public LogRunningServers(ITrigger newTrigger){

        trigger = newTrigger;
    }

    @Override
    public void execute() throws InterruptedException {
        trigger.logRunningServer();
    }
}
