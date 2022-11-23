import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //multithread to run server and client
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.serverProcess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.clientProcess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        client.start();


    }
}