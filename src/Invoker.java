import Interfaces.IAction;

//commandPattern invoker
public class Invoker {

    IAction command;

    public Invoker(IAction newCommand){
        command = newCommand;
    }

    public void run() throws InterruptedException {

        command.execute();
    }
}
