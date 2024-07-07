package network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPNetworkServer {
    /**
     * 本类为服务端，主要功能如下
     * 1. 接收客户端发送过来的消息
     * 2. 将消息转为大写，返回给客户端
     */
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(8081)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String receivedData = new String(packet.getData(), 0, packet.getLength());
            System.out.println("received data is: " + receivedData);
            byte[] message = receivedData.toUpperCase().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(message, message.length, InetAddress.getByName("localhost"), 8080);
            //响应消息
            socket.send(sendPacket);
        } catch (IOException e) {
            // pass
        }
    }
}
