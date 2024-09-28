package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <pre>
 * 关于{@link java.nio.channels.Channel}的使用
 * 1. Buffer
 *  Buffer的使用主要包含四个步骤
 *      读取数据到Buffer
 *      调用flip翻转
 *      从Buffer读取数据
 *      调用clear或compact。clear清空Buffer，compact清空读取过的数据
 * </pre>
 */
public class ChannelEx {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("./src/nio/text.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(16);
            // 从Channel中向Buffer中读取数据
            int bytesRead = channel.read(buf); // 返回值时读取的字节数
            // 因为Buffer长度的问题，可能一次读取不完
            while (bytesRead != -1) {
                System.out.println("read: " + bytesRead);
                buf.flip(); // flip将buffer从写模式切换到读模式
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();    // 清空Buffer，准备下次读取
                bytesRead = channel.read(buf);
                System.out.println();
            }

        } catch (IOException e) {
            // pass
        }
    }
}
