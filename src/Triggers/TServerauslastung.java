package Triggers;

import Interfaces.ITrigger;
import Sample.Server;
import Singletons.ServerMonitor;
import Singletons.ServerRegistry;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TServerauslastung implements ITrigger {

    private int numberOfServers = 0;
    private double averageLoadOfServers = 0;
    ArrayList<Server> registeredServers;


    private double stopLimit = 0.3;     // average % at which a server is stopped
    private double startLimit = 0.6;    // average % at which a new server is started


    @Override
    public void logRunningServer() {

    }

    @Override
    public void logAvgServerLoad() throws InterruptedException {

    }

    @Override
    public void startNewServer() throws InterruptedException {
        int serverID = 0;

        for(Server server: registeredServers){
            //breaking the servername down to characters
            // and checking if there are any numbers in the name

            if(server.getServerName().contains("triggerServer")){
                String serverName = server.getServerName();
                char[] chars = serverName.toCharArray();
                for(char c : chars){
                    if(Character.isDigit(c)){
                        if(c>serverID){
                            serverID = Integer.parseInt(String.valueOf(c));
                        }
                    }
                }
            }
        }

        ServerRegistry singletonRegistry = ServerRegistry.getInstance();

        Server simulatedServer = new Server( "triggerServer" + String.valueOf(serverID), "triggerThread" + String.valueOf(serverID), singletonRegistry);
        Thread serverThread = new Thread(simulatedServer, "triggerThread" + serverID);
        serverThread.start();

        System.out.println("TServerauslastung: startNewServer: new server(" + simulatedServer.getServerName()  + ") has been started");
        TimeUnit.MILLISECONDS.sleep(100);  //small delay necessary, so that the server can register in the serverregistry

    }

    @Override
    public void stopLowestLoad(){
        if(numberOfServers>1){
            Server lowestServer = null;
            double lowestLoad = 1;
            for(Server server : registeredServers) {
                if (server.getLoad() <= lowestLoad) {
                    lowestLoad = server.getLoad();
                    lowestServer = server;
                }
            }
            if(lowestServer != null){

                try {

                    System.out.println("TServerauslastung: stopLowestLoad: server(" + lowestServer.getServerName()  + ") is being stopped");
                    lowestServer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("TServerauslastung: could not stop server with the lowest load");
            }
        }else{
            System.out.println("TServerauslastung: can not stop server(only 1 Server active)");
        }

    }

    @Override
    public void update() throws InterruptedException {
        //updating the values in this class
        ServerMonitor singletonMonitor = ServerMonitor.getInstance();
        numberOfServers = singletonMonitor.getNumberOfServers();
        averageLoadOfServers = singletonMonitor.getAverageLoadOfServers();
        registeredServers = singletonMonitor.getRegisteredServers();

        if(averageLoadOfServers >= startLimit){
            startNewServer();
        }else if(averageLoadOfServers <= stopLimit && numberOfServers > 1) {
            stopLowestLoad();
        }

    }
    
    //configure the limit at which average server load % a server is being stopped(<30% as an example)
    public void raiseStopLimit(){
        stopLimit= stopLimit++;
    }
    
    public void lowerStopLimit(){
        stopLimit= stopLimit--;
    }

    public double getStopLimit(){
        return stopLimit;
    }
    
    //configure the limit at which average server load % a new server is being started(>60% as an example)
    public void raiseStartLimit(){
        startLimit= startLimit++;
    }
    
    public void lowerStartLimit(){
        startLimit= startLimit--;
    }
    
    public double getStartLimit(){
        return startLimit;
    }
}
