import java.io.BufferedReader; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.Socket; 
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Scannercanner;

public class Sensor {
    public static void main(String[] args) {
        if(args.length != 4) { 
            System.out.println("Usage is: " + "java Sensor <type> <initial value> <host name> <port number>"); 
            System.exit(1); 
        }
        int type = Integer.parseInt(args[0]);
        int value = Integer.parseInt(args[1]);
        String hostName = args[2];
        int portNumber = Integer.parseInt(args[3]);

        if(type < Observable.TEMP_ZONE1 || type > Observable.HUMIDITY_ZONE2) { 
            System.out.println("The simulations only works with 4 different types 0 through 3"); 
            System.exit(1); 
        } 

        try (Socket deviceSocket = new Socket(hostName, portNumber); 
        DataOutputStream out = new DataOutputStream(deviceSocket.getOutputStream()); 
        BufferedReader in = new BufferedReader(new InputStreamReader(deviceSocket.getInputStream())))  { 
        // we will describe this code below 
            // String userInput;
            // Scanner scanner = new Scanner(System.in);
            // while ((userInput = scanner.nextLine()) != null) {
            //     System.out.print("Sensor " + type + " received " + fromServer);
            //     System.out.println(userInput);
            //     System.out.println("echo: " + in.readLine());
            // }
            // scanner.close();
        } catch (UnknownHostException e) { 
            e.printStackTrace(); 
            } catch (IOException e) {  e.printStackTrace(); 
        }
    }
}
