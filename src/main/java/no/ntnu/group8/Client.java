package no.ntnu.group8;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    private final DatagramSocket clientSocket;
    private final String address;
    private final int port;

    /**
     *
     * This is the client which communicates with the server.
     *
     * @param address address to
     * @param port
     * @throws SocketException
     */
    public Client(String address, int port) throws SocketException {
        this.address = address;
        this.port = port;
        clientSocket = new DatagramSocket();
    }

    public String sendMessage(String message) throws IOException {
        byte[] messageByte = message.getBytes(StandardCharsets.UTF_8);
        clientSocket.connect(InetAddress.getByName(address), port);
        DatagramPacket packet = new DatagramPacket(messageByte, messageByte.length, InetAddress.getByName(address), port);
        clientSocket.send(packet);
        byte[] receiveMessageByte = new byte[1024];
        packet = new DatagramPacket(receiveMessageByte, receiveMessageByte.length);
        clientSocket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
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
        } else {
            SOrQ = "null";
        }
        return SOrQ;
    }

    /**
     * Check how many words there are in the string.
     * @param text The text to check
     * @return Number of words as an Int
     */
    public int wordCount(String text) {
        StringBuilder sb = new StringBuilder(text);
        sb.deleteCharAt(text.length() - 1);
        int count = 0;
        String[] splited = sb.toString().split("\\s+");
        for (int i = 0; i < splited.length; i++) {
            if (splited[i].matches("[a-zA-Z]+")) {
                count++;
            }
        }
        return count;
    }

}
