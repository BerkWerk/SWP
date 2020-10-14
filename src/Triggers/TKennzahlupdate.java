package Triggers;

import Interfaces.ITrigger;
import Singletons.Logger;
import Singletons.ServerMonitor;

public class TKennzahlupdate implements ITrigger {

    private int numberOfServers = 0;
    private double averageLoadOfServers = 0;
    boolean logAvg = true;
    boolean logServers = true;


    @Override
    public void logRunningServer() throws InterruptedException {
        Logger singletonLogger = Logger.getInstance();
        singletonLogger.log("RegisteredServers: " + numberOfServers);
        System.out.println("TKennzahlupdate: logged : RegisteredServers: " + numberOfServers);
    }

    @Override
    public void logAvgServerLoad() throws InterruptedException {
        Logger singletonLogger = Logger.getInstance();
        singletonLogger.log("Average server load: " + averageLoadOfServers);
        System.out.println("TKennzahlupdate: logged : Average server load: " + averageLoadOfServers);
    }

    @Override
    public void startNewServer() {

    }

    @Override
    public void stopLowestLoad() {

    }

    @Override
    public void update() throws InterruptedException {

        ServerMonitor singletonMonitor = ServerMonitor.getInstance();
        numberOfServers = singletonMonitor.getNumberOfServers();
        averageLoadOfServers = singletonMonitor.getAverageLoadOfServers();

        if(logAvg == true){
            logAvgServerLoad();
        }
        if(logServers == true){
            logRunningServer();
        }
    }

    public void setLogAvgOn(){
        logAvg=true;
    }

    public void setLogAvgOff(){
        logAvg=false;
    }

    public void setLogServersOn(){
        logServers=true;
    }

    public void setLogServersOff(){
        logServers=false;
    }
}
