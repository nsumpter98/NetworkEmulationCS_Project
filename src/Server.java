import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /* The point-to-point links specified at the ITC script will be emulated by passing UDP/IP messages
     between two directly connected network nodes. For instance, if node-1 is connected to node-2,
     any message that node-1 sends to node-2 will have to be encapsulated in a UDP/IP packet and
     sent to node-2 through its UDP socket as defined in the ITC script. A very basic implementation
     of a “UDP Sender Receiver” emulated link in Java will be provided by us for reference.*/
    public static void serverProcess() throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println("Server is running...");
            serverSocket = new ServerSocket(10008);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));

        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Server: " + inputLine);
            outputLine = inputLine;
            out.println(outputLine.split("-")[1]);
            if (outputLine.equals("Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }


}
