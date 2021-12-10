package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class ClientThread implements Runnable {
    private String hostName;
    private int hostPort;

    public ClientThread(String hostName, int hostPort){
        this.hostName = hostName;
        this.hostPort = hostPort;
    }

    public void run(){
        Socket echoSocket = null;
        ObjectOutputStream out = null;
        BufferedReader in = null;

        try{
            InetAddress host = InetAddress.getByName(hostName);
            echoSocket = new Socket(host, hostPort);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            Transport[] transports = {
                    new Auto("Kia", 2),
                    new Motorbike("BMW", 2),
                    new Moped("Joker", 2),
            };

            out.writeObject(transports);
            System.out.println("echo: " + in.readLine());

            out.flush();
            in.close();
            echoSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
}
