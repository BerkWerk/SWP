package Singletons;

import Sample.Server;

import java.util.*;
import java.util.concurrent.TimeUnit;

//singletonPattern
public class ServerRegistry {

    private ArrayList<Server> registeredServers = new ArrayList<Server>();
//    private List<Server> registeredServers;

    private static ServerRegistry firstInstance = null;

    private ServerRegistry() { }

    // static ... methode ist dadurch an klasse gebunden
    // es gibt die methode NUR 1 mal!
    public static ServerRegistry getInstance() {

        if(firstInstance == null) {
            synchronized (ServerRegistry.class) {
                if (firstInstance == null) {
                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    firstInstance = new ServerRegistry();

                }
            }
        }
        // Under either circumstance this returns the instance
        return firstInstance;
    }

    public void registerServer(Server newServer){
        System.out.println("New server has registered in ServerRegistry: " + newServer.getServerName());
        registeredServers.add(newServer);
    }

    public void unregisterServer(Server newServer){
        registeredServers.remove(newServer);
    }

    public void printServer(){

        if(registeredServers.size()>0){
            if(registeredServers!= null){
                for(Server server : registeredServers){
                    System.out.println(server.getServerName() + " is online");
                }
            }
        }else{
            System.out.println("ServerRegistry: no registered Servers");
        }


    }

    public ArrayList<Server> getRegisteredServers(){
        if(registeredServers.size()>0){
            if(registeredServers!= null){
                return registeredServers;
            }
        }

        System.out.println("ServerRegistry: no registered Servers to get");

        return null;
    }
}

