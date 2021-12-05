package Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadServer {
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
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
                pool.execute(clientHandler);
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
