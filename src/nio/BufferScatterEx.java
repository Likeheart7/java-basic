package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 关于{@link java.nio.Buffer} 的Scatter和Gather的操作
 * Scatter实际上就是将channel的数据读取到的多个Buffer，这可能需要不同部分的长度是确定的。
 */
public class BufferScatterEx {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("./src/nio/httptext.txt", "r")) {
            FileChannel channel = file.getChannel();
            ByteBuffer header = ByteBuffer.allocate(96);
            ByteBuffer body = ByteBuffer.allocate(80);
            ByteBuffer[] bufferArray = {header, body};
            long read = channel.read(bufferArray);
            header.flip();
            body.flip();
            while(header.hasRemaining()) {
                System.out.print((char) header.get());
            }
            while(body.hasRemaining()) {
                System.out.print((char) body.get());
            }
        } catch (IOException e) {
            // pass
        }

    }
}
