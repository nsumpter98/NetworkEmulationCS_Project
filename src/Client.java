import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {
    //client to connect to server port 10008
    public static void clientProcess() throws IOException {
        //udp client in loop
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        while(true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFromUser.readLine();
            sendData = garble(sentence).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
        }






    }

    //create a message garbled by a random number of random characters
    public static String garbleMessage(String message) {
        String garbledMessage = "";
        int randomNum = (int) (Math.random() * 10);
        for (int i = 0; i < randomNum; i++) {
            garbledMessage += (char) (Math.random() * 256);
        }
        garbledMessage += message;
        return garbledMessage;
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
