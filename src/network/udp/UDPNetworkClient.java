package network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPNetworkClient {
    /**
     * 本类为客户端，实现以下功能
     * 1. 从控制台读取一行英文字符，将数据发给服务端
     * 2. 服务端会将接收到的数据转为大写，返回给客户端。
     * 3. 客户端打印接收到的数据至控制台。
     * <p>
     * 注意，因为UDP是无连接的，所以，在服务器未启动的情况下，客户端发送的消息都会丢失
     */
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(8080)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入要发送的消息：");
            byte[] message = scanner.nextLine().getBytes(StandardCharsets.UTF_8);
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(message, message.length, address, 8081);
            socket.send(packet);
            byte[] respBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(respBuffer, respBuffer.length);
            socket.receive(receivePacket);
            System.out.println("从服务端接收到的响应消息是：" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
        } catch (IOException e) {
            // pass
        }
    }
}
