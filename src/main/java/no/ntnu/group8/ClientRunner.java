package no.ntnu.group8;

import java.net.SocketException;

public class ClientRunner {
    public ClientRunner() {
        try {
            Client client = new Client("129.241.152.12", 1234);
        } catch (SocketException e) {
            System.err.println("Error: Socket not created correctly");
        }
    }
}
