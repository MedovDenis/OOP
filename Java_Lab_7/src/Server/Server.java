package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket clientSocket = serverSocket.accept();

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
