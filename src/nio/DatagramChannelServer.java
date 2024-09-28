package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * {@link java.nio.channels.DatagramChannel} 的示例。
 */
public class DatagramChannelServer {
    public static void main(String[] args) throws IOException {
        // 开启channel
        DatagramChannel channel = DatagramChannel.open();
        // 绑定监听端口
        channel.socket().bind(new InetSocketAddress(9999));
        ByteBuffer buffer = ByteBuffer.allocate(8);
        while (true) {
            // 清空
            buffer.clear();
            SocketAddress socket = channel.receive(buffer); // 接收数据，放不下的部分直接丢弃。
            // 翻转，准备读取
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
        }
    }
}
