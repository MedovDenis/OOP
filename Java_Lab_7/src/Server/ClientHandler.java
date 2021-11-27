package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        System.out.println("Client connected!!!");
    }

    public void run(){
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            Transport[] transports = new Transport[0];
            try {
                transports = (Transport[]) in.readObject();
                double avg = Transports.getAvaragePriceTransports(transports);
                out.println(avg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            out.close();
            in.close();
            clientSocket.close();
            System.out.println("Client disconnected!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
