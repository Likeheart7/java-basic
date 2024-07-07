package network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPNetworkServer {
    /**
     * 本类为服务端，主要功能如下
     * 1. 接收客户端发送过来的消息
     * 2. 将消息转为大写，返回给客户端
     */
    public static void main(String[] args) {
        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务端启动在 " + port + " 端口");
            while (true) {
                Socket accepted = serverSocket.accept();
                System.out.println("有客户端连接进来");
                // 处理请求
                // 输入流
                BufferedReader in = new BufferedReader(new InputStreamReader(accepted.getInputStream()));
                // 写出流
                PrintWriter out = new PrintWriter(accepted.getOutputStream(), true);
                String message = in.readLine();
                System.out.println("服务器接收到信息是：" + message);
                String sendMessage = message.toUpperCase();
                // autoFlush为true，调用println方法时，无论是否包含换行符，PrintWriter都会自动刷新缓冲区。
                out.println("Echo: " + sendMessage);
                accepted.close();
            }
        } catch (IOException e) {
            // pass
        }
    }
}
