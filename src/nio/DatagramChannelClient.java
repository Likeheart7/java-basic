package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel发送数据
 */
public class DatagramChannelClient {
    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.put("flymetothemoon.".getBytes());
            buffer.flip();
            channel.connect(new InetSocketAddress("localhost",9999));
            channel.write(buffer);
        } catch (IOException e) {
            // pass
        }
    }
}
