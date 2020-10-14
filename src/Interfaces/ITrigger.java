package Interfaces;

import Singletons.ServerMonitor;

//trigger interface with update command(observer pattern)
 public interface ITrigger {

//commands for triggers
     void logRunningServer() throws InterruptedException;

     void logAvgServerLoad() throws InterruptedException;

     void startNewServer() throws InterruptedException;

     void stopLowestLoad() throws InterruptedException;

    // The Observers update method is called when the Interfaces.Subject changes
     void update() throws InterruptedException;

}
