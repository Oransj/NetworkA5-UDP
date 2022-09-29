package no.ntnu.group8;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    private final DatagramSocket clientSocket;
    private String address;
    private int port;

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

}
