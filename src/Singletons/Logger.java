package Singletons;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//singletonPattern
public class Logger {

    private double averageLoad = 0.0;

    private static Logger firstInstance = null;

    private Logger() {}   //constructor has to be empty -> singleton, load data after init

    public static Logger getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized (Logger.class) {
                if (firstInstance == null) {
  
                    firstInstance = new Logger();
                    
                }
            }
        }
        return firstInstance;
    }
    
    public void log(String message){

        // capturing and formatting time and date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //try block, can write into a text file several times
        try(FileWriter fw = new FileWriter("logFile.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){

            //adding date/time/name, formatting and writing message into text file
            out.println("[" +dtf.format(now)+"]: " + message);

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IOException writing to log file.");
        }
    }
}
