import Action.LogAvgServerLoad;
import Action.StartNewServer;
import Interfaces.ITrigger;
import Sample.Server;
import Singletons.ServerMonitor;
import Singletons.ServerRegistry;
import Triggers.TKennzahlupdate;
import Triggers.TServerauslastung;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException {


        //period in which observer is monitoring the subject(serverRegistry)
        // implemented in main, this way ServerMonitor.class can be a singleton and
        // does not need to implement a thread or timer to execute parallel to the rest of the program

        ServerRegistry singletonRegistry = ServerRegistry.getInstance();
        ServerMonitor singletonMonitor = ServerMonitor.getInstance();
//
//        //--------------------------------------testing startnewserver
//        //first server
//        Server simulatedServer1 = new Server("server1", "thread1", singletonRegistry, 0.9);
//        Thread serverThread = new Thread(simulatedServer1, "T1");
//        serverThread.start();
//
//        TimeUnit.MILLISECONDS.sleep(100);  //small delay necessary, so that the server can register in the serverregistry

        //--------------------------------------testing stoplowestload

        //first server
        Server simulatedServer1 = new Server("server1", "thread1", singletonRegistry, 0.4);
        Thread serverThread1 = new Thread(simulatedServer1, "T1");
        serverThread1.start();
        //second server
        Server simulatedServer2 = new Server("server2", "thread2", singletonRegistry, 0.1);
        Thread serverThread2 = new Thread(simulatedServer2, "T2");
        serverThread2.start();

        TimeUnit.MILLISECONDS.sleep(100);  //small delay necessary, so that the server can register in the serverregistry


        //creating a trigger
        ITrigger newTrigger = new TKennzahlupdate();
        //executing action on trigger
//        LogAvgServerLoad logAvgLoad = new LogAvgServerLoad(newTrigger);
//        Invoker logCommand = new Invoker(logAvgLoad);
        //registering
        singletonMonitor.register(newTrigger);

        //creating a trigger
        ITrigger newTrigger2 = new TServerauslastung();
        //executing action on trigger
//        StartNewServer startServer = new StartNewServer(newTrigger2);
//        Invoker startCommand = new Invoker(startServer);
        //registering
        singletonMonitor.register(newTrigger2);


        //executes the code every 2 seconds
        new Timer().scheduleAtFixedRate(new TimerTask(){
            public void run(){
                singletonMonitor.monitorRegistry(singletonRegistry);

                try {
                    singletonMonitor.notifyObserver();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,2000);

        // ========================================================
        //testing servers

//        ServerRegistry singletonRegistry = ServerRegistry.getInstance();
//
//        //first server
//        Server simulatedServer1 = new Server("server1", "thread1", singletonRegistry);
//        Thread serverThread = new Thread(simulatedServer1, "T1");
//        serverThread.start();
//
//        //singletonRegistry.registerServer(simulatedServer1);   //serverobject registering itself in server registry
//
//        //second server
//        Server simulatedServer2 = new Server("server2", "thread2", singletonRegistry);
//        Thread serverThread2 = new Thread(simulatedServer2, "T2");
//        serverThread2.start();
//
//        //singletonRegistry.registerServer(simulatedServer2);   //serverobject registering itself in server registry
//
//        ServerMonitor singletonMonitor = ServerMonitor.getInstance();  //accessing serverRegistry(singleton)
//
//        TimeUnit.SECONDS.sleep(4);
//
//        simulatedServer2.stop();
//
//        TimeUnit.SECONDS.sleep(4);
//
//        //third server
//        Server simulatedServer3 = new Server("server3", "thread3", singletonRegistry);
//        Thread serverThread3 = new Thread(simulatedServer3, "T3");
//        serverThread3.start();
//
//        //fourth server
//        Server simulatedServer4 = new Server("server4", "thread4", singletonRegistry);
//        Thread serverThread4 = new Thread(simulatedServer4, "T4");
//        serverThread4.start();


    }
}
