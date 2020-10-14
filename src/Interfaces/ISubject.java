package Interfaces;
// This interface handles adding, deleting and updating
// all observers

public interface ISubject {

    public void register(ITrigger newTrigger);
    public void unregister(ITrigger removeTrigger);
    public void notifyObserver() throws InterruptedException;

}
