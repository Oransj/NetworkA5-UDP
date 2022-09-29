package no.ntnu.group8;

import java.io.IOException;
import java.net.SocketException;

public class ClientRunner {
    public ClientRunner() {
        try {
            Client client = new Client("129.241.152.12", 1234);
            String receivedString = client.sendMessage("task");
            int i = 0;
            while (i < 3) {
                System.out.println(receivedString);
                String sendString = client.checkSOrQ(receivedString) + " " + client.wordCount(receivedString);
                System.out.println("Sendstring: " + sendString);
                receivedString = client.sendMessage(sendString);
                i++;
            }
        } catch (SocketException e) {
            System.err.println("Error: Socket not created correctly." + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientRunner();
    }

}
