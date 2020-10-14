package Action;

import Interfaces.IAction;
import Interfaces.ITrigger;

//command in commandPattern
public class StartNewServer implements IAction {

    ITrigger trigger;

    public StartNewServer(ITrigger newTrigger){

        trigger = newTrigger;
    }

    @Override
    public void execute() throws InterruptedException {
        trigger.startNewServer();
    }
}
