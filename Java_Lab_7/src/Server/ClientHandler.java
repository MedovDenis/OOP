package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        System.out.println("Client connected");
    }

    public void run(){
        PrintWriter out = null;
        ObjectInputStream in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new ObjectInputStream(clientSocket.getInputStream());

            Transport[] transports = (Transport[]) in.readObject();
            double avg = Transports.getAvaragePriceTransports(transports);
            out.println(avg);

            out.flush();
            in.close();
            clientSocket.close();
            System.out.println("Client disconnected");

        } catch (ClassNotFoundException | IOException e ) {
            e.printStackTrace();
        }
        finally {
            try {
                out.flush();
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
