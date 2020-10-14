package Sample;

import Singletons.ServerRegistry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Server implements Runnable{


    private String serverName;
    private String threadName;
    private volatile boolean exit = false;  //bool to check if the serverThread has to be shutdown
    private double load = 0;                //load changes over time just like in the assignment information(angabe)

    private ServerRegistry serverRegistry = null;

    public Server(String serverNameArg, String threadNameArg, ServerRegistry newRegistry){

        this.serverName = serverNameArg;
        this.threadName = threadNameArg;
        this.serverRegistry = newRegistry;

        //initial calculation of server load %
        load = Math.random() * (1.0);  // Generates a random number between 0.0 and 1.0
        load = BigDecimal.valueOf(load)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        // Generates a random number between -.03 and .03
        //double randNum = (Math.random() * (.06)) - .03;

    }

    public Server(String serverNameArg, String threadNameArg, ServerRegistry newRegistry, double newLoad){

        this.serverName = serverNameArg;
        this.threadName = threadNameArg;
        this.serverRegistry = newRegistry;
        this.load = newLoad;

    }

    public void run(){

        serverRegistry.registerServer(this);

        while(!exit){

            System.out.println("Thread (" + threadName + ") on Server: " + serverName + ", is running with " + load + " load" );
            try{
                Thread.sleep(2000);
            }catch(InterruptedException exit){
                break;
            }
        }
        System.out.println("Thread(" + threadName + ") on Server: " + serverName + ", is stopping ...");
    }

    public void stop(){
        if(serverRegistry!=null){
            serverRegistry.unregisterServer(this);
            exit = true;
        }else{
            System.out.println("Could not unregister/stop server");
        }
    }

    //only for testing purpose
    public void setLoad(double newLoad){
        load = newLoad;
    }

    public String getServerName(){
        return serverName;
    }

    public double getLoad(){
        return load;
    }
}
