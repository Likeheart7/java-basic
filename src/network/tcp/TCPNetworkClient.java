package network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPNetworkClient {
    /**
     * 本类为客户端，实现以下功能
     * 1. 从控制台读取一行英文字符，将数据发给服务端
     * 2. 服务端会将接收到的数据转为大写，返回给客户端。
     * 3. 客户端打印接收到的数据至控制台。
     * <p>
     * 因为TCP是面向连接的，所以在服务端启动前尝试发送消息，会抛出异常，异常信息为：Connection refused: connect
     */
    public static void main(String[] args) {
        String targetHost = "localhost";
        int targetPort = 8081;
        try (
                Socket socket = new Socket(targetHost, targetPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))

        ) {
            System.out.print("请输入要发送的消息: ");
            String message = stdIn.readLine();
            // 将消息写出
            out.println(message);
            // 读取响应
            String receivedMessage = in.readLine();
            System.out.println("Server 响应的消息是： " + receivedMessage);
        } catch (IOException e) {
            System.out.println("出现异常：" + e.getMessage());
        }
    }
}
