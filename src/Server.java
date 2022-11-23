import java.io.*;
import java.net.*;

public class Server {
    /* The point-to-point links specified at the ITC script will be emulated by passing UDP/IP messages
     between two directly connected network nodes. For instance, if node-1 is connected to node-2,
     any message that node-1 sends to node-2 will have to be encapsulated in a UDP/IP packet and
     sent to node-2 through its UDP socket as defined in the ITC script. A very basic implementation
     of a “UDP Sender Receiver” emulated link in Java will be provided by us for reference.*/
    public static void serverProcess() throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("RECEIVED: " + sentence.split("~")[1]);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.split("~")[1];
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }


}
