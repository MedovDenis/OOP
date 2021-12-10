package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class Client {
    private static final String hostName = "localhost";
    private static final int hostPort = 4450;

    public static void main(String[] args){
        //SingleClient();
        MultiThreadClient();
    }

    public static void SingleClient(){
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

    public static void MultiThreadClient(){
        for (int i = 0; i < 10; i++){
            Runnable client = new ClientThread(hostName, hostPort);
            Thread threadClient = new Thread(client);
            threadClient.start();
        }
    }
}
