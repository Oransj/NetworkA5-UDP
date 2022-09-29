package no.ntnu.group8;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    private final DatagramSocket clientSocket;
    private final String address;
    private final int port;

    public Client(String address, int port) throws SocketException {
        this.address = address;
        this.port = port;
        clientSocket = new DatagramSocket();
    }

    public String sendMessage(String message) throws IOException {
        byte[] messageByte = message.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(messageByte, messageByte.length, InetAddress.getByName(address), port);
        clientSocket.send(packet);
        packet = new DatagramPacket(messageByte, messageByte.length);
        clientSocket.receive(packet);
        return Arrays.toString(packet.getData());
    }
    
    /**
     * Check if the text is a statement (S) or a question (Q)
     *
     * @param text The text to check
     * @return The category of the text
     */
    public String checkSOrQ(String text) {
        String SOrQ = "";
        if (text.charAt(text.length()-1) == '.') {
            SOrQ = "statement";
        } else if (text.charAt(text.length()-1) == '?') {
            SOrQ = "question";
        }
        return SOrQ;
    }

}
