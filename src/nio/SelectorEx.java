package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * todo: 不是每次都能响应成功。
 * {@link java.nio.channels.Selector} 监听多个Channel的使用
 */
public class SelectorEx {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        ServerSocket serverSocket1 = serverSocketChannel1.socket();
        serverSocket1.bind(new InetSocketAddress(7777));
        serverSocketChannel1.configureBlocking(false);
        serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket2 = serverSocketChannel2.socket();
        serverSocket2.bind(new InetSocketAddress(8080));
        serverSocketChannel2.configureBlocking(false);
        serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println(Thread.currentThread().getName() + "=====>>> Selector 监听 8080 和 7777 两个端口......");
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    System.out.println(Thread.currentThread().getName() + "  " + serverSocketChannel.getLocalAddress() + " 接收到一个连接，来源是：" + socketChannel.getRemoteAddress());
                }

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int count = channel.read(buffer);
                    while (count > 0) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            System.out.print((char) buffer.get());
                        }
                        buffer.clear();
                        count = channel.read(buffer);
                    }
                    System.out.println();
                }
                if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer out = generateResponse();
                    while (out.hasRemaining()) {
                        int result = channel.write(out);
                        System.out.println(Thread.currentThread().getName() + " 写入结果为：" + result);
                    }
                    channel.close();
                }
                iterator.remove(); // 移除已经处理的key
            }
        }
    }

    /**
     * 构造HTTP响应体
     */
    private static ByteBuffer generateResponse() {
        // 响应体
        String body = "<html><body><h1>ECHO</h1></body></html>";
        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);

        // 构造 HTTP 响应
        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + bodyBytes.length + "\r\n" +
                "\r\n";

        // 将 HTTP 头部和 body 合并
        ByteBuffer responseBuffer = ByteBuffer.allocate(httpResponse.length() + bodyBytes.length);
        responseBuffer.put(httpResponse.getBytes(StandardCharsets.UTF_8));
        responseBuffer.put(bodyBytes);

        // 将缓冲区切换为读取模式，准备写入到客户端
        responseBuffer.flip();
        return responseBuffer;
    }
}
