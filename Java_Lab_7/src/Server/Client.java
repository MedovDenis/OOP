package Server;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        InetAddress host = InetAddress.getByName("192.168.0.103");

        try {
            echoSocket = new Socket(host, 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.printf("Don't know about host: %s%n", host.getHostAddress());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.printf("Couldn't get I/O for the connection to: %s%n", host.getHostAddress());
            System.exit(1);
        }

        System.out.println("Connected");

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
