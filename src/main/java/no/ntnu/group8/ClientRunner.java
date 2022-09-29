package no.ntnu.group8;

import java.net.SocketException;

public class ClientRunner {
    public ClientRunner() {
        try {
            Client client = new Client();
        } catch (SocketException e) {
            System.out.println("Error: Socket not created correctly");
        }
    }
}
