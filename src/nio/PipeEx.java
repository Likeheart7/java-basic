package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * {@link  Pipe} 通过pipe.sink获取写入管道，通过pipe.source获取读取管道，可以在多个线程之间传递消息。
 */
public class PipeEx {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        new Thread(() -> {
            try {
                sinkChannel.write(ByteBuffer.wrap("What are you waiting for".getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "write-thread").start();
        new Thread(() -> {
            try {
                while (true) {
                    Pipe.SourceChannel source = pipe.source();
                    ByteBuffer buf = ByteBuffer.allocate(20);
                    int read = source.read(buf);
                    if (read > 0) {
                        System.out.print(Thread.currentThread().getName() + "接收数据: ");
                        buf.flip();
                        while (buf.hasRemaining()) {
                            System.out.print((char) buf.get());
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "read-thread").start();
    }
}
