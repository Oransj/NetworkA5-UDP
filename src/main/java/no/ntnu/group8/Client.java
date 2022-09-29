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
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        boolean running = true;
        while (running && i < 10) {
            DatagramPacket packet = new DatagramPacket(messageByte, messageByte.length, InetAddress.getByName(address), port);
            System.err.println("DatagramPacket successfully created");
            clientSocket.send(packet);
            System.err.println("Packet successfully sent");
            packet = new DatagramPacket(messageByte, messageByte.length);
            clientSocket.receive(packet);
            System.err.println("Packet successfully received");
            System.err.println("Packet data: " + Arrays.toString(packet.getData()));
            String returnValue = new String(packet.getData(), 0, packet.getLength());
            if (returnValue.equals("end")) {
               running = false;
            } else {
                stringBuilder.append(returnValue);
                i++;
            }
        }
        return stringBuilder.toString();
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

    /**
     * Check how many words there are in the string.
     * @param text The text to check
     * @return Number of words as an Int
     */
    public int wordCount(String text) {
        int count = 0;
        String[] splited = text.split("\\s+");
        for (int i = 0; i < splited.length; i++) {
            if (splited[i].matches("[a-zA-Z]+")) {
                count++;
            }
        }
        return count;
    }

}
