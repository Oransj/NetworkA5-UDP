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
        DatagramPacket packet = new DatagramPacket(messageByte, messageByte.length, InetAddress.getByName(address), port);
        System.err.println("DatagramPacket successfully created");
        clientSocket.send(packet);
        System.err.println("Packet successfully sent");
        packet = new DatagramPacket(messageByte, messageByte.length);
        clientSocket.receive(packet);
        System.err.println("Packet successfully received");
        System.err.println("Packet data: " + Arrays.toString(packet.getData()));
        String returnValue = new String(packet.getData(), 0, packet.getLength());
        return returnValue;
    }

}
