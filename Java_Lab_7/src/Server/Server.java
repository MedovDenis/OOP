package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }

        PrintWriter out = null;
        ObjectInputStream in = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Transport[] transports = (Transport[]) in.readObject();
            double avg = Transports.getAvaragePriceTransports(transports);
            out.println(avg);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
