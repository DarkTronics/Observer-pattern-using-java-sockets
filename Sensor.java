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
    }
}
