package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 通过transferTo或transferFrom方法在Channel之间传递数据。
 */
public class ChannelTransfer {
    public static void main(String[] args) {
        try (
                RandomAccessFile fromFile = new RandomAccessFile("./src/nio/text.txt", "r");
                RandomAccessFile toFile = new RandomAccessFile("./src/nio/textCopy.txt", "rw")
        ) {
            FileChannel fromChannel = fromFile.getChannel();
            FileChannel toChannel = toFile.getChannel();
            long dataSize = fromChannel.size();
//            fromChannel.transferTo(0, dataSize, toChannel);
            toChannel.transferFrom(fromChannel, 0, dataSize);
        } catch (IOException e) {
            // pass
        }
    }
}
