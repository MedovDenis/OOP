package Server;

import Interface.Transport;
import Transport.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        // SingleThreadServer();
        MultiThreadServer();
    }

    public static void SingleThreadServer (){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ServerSocket serverSocket = new ServerSocket(4444);

            while (!serverSocket.isClosed()){
                if (br.ready()){
                    System.out.println("Hay");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        serverSocket.close();
                        br.close();
                        break;
                    }
                }

                Socket clientSocket = serverSocket.accept();

                System.out.println("Connected");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                try {
                    Transport[] transports = (Transport[]) in.readObject();
                    double avg = Transports.getAvaragePriceTransports(transports);
                    out.println(avg);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                System.out.println("Disconnected");
                out.close();
                in.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MultiThreadServer (){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ServerSocket serverSocket = new ServerSocket(4444);

            while (!serverSocket.isClosed()){
                if (br.ready()){
                    System.out.println("Hay");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        serverSocket.close();
                        br.close();
                        break;
                    }
                }

                Socket clientSocket = serverSocket.accept();
                Runnable clientHandler = new ClientHandler(clientSocket);
                Thread threadClient = new Thread(clientHandler);
                threadClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
