import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    //client to connect to server port 10008
    public static void clientProcess() throws IOException {
        //ACK counter
        int ackCounter = 0;
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

            System.out.println("Client: " + userInput);
            out.println(garble(send(userInput) + "-" + ackCounter));
            //increment ACK counter
            ackCounter++;
            //randomly do not send message
           /* if (Math.random() < 0.5) {
                System.out.println("Client: " + userInput);
                out.println(garble(send(userInput)));
            }
            else {
                System.out.println("Client: " + userInput + " (not sent)");
                continue;

            }*/





            System.out.println("Client Send: " + userInput);
            System.out.println("Server Response: " + in.readLine());
            if (userInput.equals("Bye."))
                break;


        }
        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
    }

    //create message garbled by random bit error
    public static String garble(String message) {
        String garbledMessage = "";
        for (int i = 0; i < message.length(); i++) {
            if (Math.random() < 0.1) {
                garbledMessage += (char) (message.charAt(i) + 1);
            } else {
                garbledMessage += message.charAt(i);
            }
        }
        return garbledMessage;
    }


    //create send function that adds the checksum to the message
    public static String send(String message) {
        String checksum = "";
        for (int i = 0; i < message.length(); i++) {
            checksum += (char) (message.charAt(i) + 1);
        }
        return message + checksum;
    }



}
