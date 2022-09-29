package no.ntnu.group8;

import java.io.IOException;
import java.net.SocketException;

public class ClientRunner {
    public ClientRunner() {
        try {
            Client client = new Client("129.241.152.12", 1234);
            int i = 0;
            while (i < 3) {
                String receivedString = client.sendMessage("task");
                System.out.println(receivedString);
                String sendString = client.checkSOrQ(receivedString) + " " + client.wordCount(receivedString);
                System.out.println("Sendstring: " + sendString);
                System.out.println(client.sendMessage(sendString) + "\n");
                i++;
            }
        } catch (SocketException e) {
            System.err.println("Error: Socket not created correctly");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientRunner();
    }

}
