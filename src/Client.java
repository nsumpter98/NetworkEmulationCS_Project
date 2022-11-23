import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    //client to connect to server port 10008
    public static void clientProcess() throws IOException {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 10008);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Client: " + userInput);
            System.out.println("Server: " + in.readLine());
            if (userInput.equals("Bye."))
                break;
        }
        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
    }
}
