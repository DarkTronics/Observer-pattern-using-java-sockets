import java.io.BufferedReader; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.Socket; 
import java.net.UnknownHostException;

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
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(deviceSocket.getInputStream())))  { 
        // we will describe this code below 
            String fromServer;
            while ((fromServer = stdIn.readLine()) != null) {
                System.out.print("Sensor " + type + " received " + fromServer);
                if(fromServer == "ID") {
                    out.writeInt(type);
                }
                if(fromServer == "VAL") {
                    if(Math.random() <= 0.5) {
                        value += 1;
                    }
                    else {
                        value -= 1;
                    }
                    out.writeDouble(value);
                }
                if(fromServer == "SETNORM") {
                    if(type == Observable.TEMP_ZONE1 || type == Observable.TEMP_ZONE2) {
                        value = Observable.NORMAL_TEMP;
                    }
                    else {
                        value = Observable.NORMAL_HUMIDITY;
                    }
                    out.writeDouble(value);
                }
                System.out.println(". Value = " + value);
            }
        } catch (UnknownHostException e) { 
            e.printStackTrace(); 
            } catch (IOException e) {  e.printStackTrace(); 
        }
    }
}
