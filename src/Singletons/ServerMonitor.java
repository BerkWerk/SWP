package Singletons;

import Interfaces.ISubject;
import Interfaces.ITrigger;
import Sample.Server;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//singletonPattern and subject in observerPattern
public class ServerMonitor implements ISubject{

    private ArrayList<ITrigger> observers = new ArrayList<>();
    private int numberOfServers;
    private double averageLoadOfServers;
    private ArrayList<Server> registeredServers;

    private static ServerMonitor firstInstance = null;

    private ServerMonitor() {}   //constructor has to be empty -> singleton, load data after init

    public static ServerMonitor getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized(ServerMonitor.class){
                if(firstInstance == null) {
                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    firstInstance = new ServerMonitor();

                }
            }
        }
        return firstInstance;
    }

    public void monitorRegistry(ServerRegistry newRegistry){
        //this function counts the number of servers and computes their average load
        if(newRegistry.getRegisteredServers()!= null){
            registeredServers = newRegistry.getRegisteredServers();  //monitoring all registered servers from registry
            numberOfServers = registeredServers.size();    //getting number of servers

            double loadSum = 0;
            for(Server server: registeredServers){

                loadSum += server.getLoad();    //getting load values from all registered(online) servers
            }

            averageLoadOfServers = loadSum/numberOfServers;  //calculating average server load

            averageLoadOfServers = BigDecimal.valueOf(averageLoadOfServers)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }else{
            System.out.println("monitorRegistry: serverList empty");
        }


    }

    public int getNumberOfServers(){
        return numberOfServers;
    }

    public double getAverageLoadOfServers(){
        return  averageLoadOfServers;
    }

    public ArrayList<Server> getRegisteredServers() {
        return registeredServers;
    }

    @Override
    public void register(ITrigger newObserver) {

        // Adds a new observer to the ArrayList
        observers.add(newObserver);
    }

    @Override
    public void unregister(ITrigger deleteObserver) {
        // Get the index of the observer to delete
        int observerIndex = observers.indexOf(deleteObserver);
        // Print out message (Have to increment index to match)
        System.out.println("Interfaces.Trigger " + observerIndex + " deleted");
        // Removes observer from the ArrayList
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() throws InterruptedException {
        // Cycle through all observers(triggers in our case) and notifies them of changes
        for(ITrigger observer: observers){
            System.out.println(observer);

            observer.update();
        }
    }
}
