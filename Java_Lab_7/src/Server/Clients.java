package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class Clients {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        ObjectOutputStream out = null;
        BufferedReader in = null;
        InetAddress host = InetAddress.getByName("localhost");

        try {
            echoSocket = new Socket(host, 4444);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.printf("Don't know about host: %s%n", host.getHostAddress());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.printf("Couldn't get I/O for the connection to: %s%n", host.getHostAddress());
            System.exit(1);
        }

        Transport[] transports = {
                new Auto("Kia", 2),
                new Motorbike("BMW", 2),
                new Moped("Joker", 2),
        };

        out.writeObject(transports);
        System.out.println("echo: " + in.readLine());

        out.close();
        in.close();
        echoSocket.close();
    }
}
