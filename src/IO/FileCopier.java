package IO;

import org.apache.commons.lang.time.StopWatch;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileCopier {
    public static void main(String[] args) {
        var filePath = System.getProperties().get("user.home") + "/desktop/file.txt";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        fileCopyWithIOStream(filePath);

        stopWatch.stop();
        System.out.println("IOStream: " + stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();

        fileCopyWithIOBufferStream(filePath);

        stopWatch.stop();
        System.out.println("IOBufferStream: " + stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();

        fileCopyWithFileChannel(filePath);

        stopWatch.stop();
        System.out.println("FileChannel: " + stopWatch.getTime());

        /**
         * 运行结果
         * IOStream: 6872
         * IOBufferStream: 1362
         * FileChannel: 670
         */
    }

    private static void fileCopyWithFileChannel(String filePath) {
        try (
                var in = new FileInputStream(filePath);
                var out = new FileOutputStream("C:\\Users\\chenxing\\Desktop\\channelCopy.txt")
        ){
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedInputStream 是将更多内容提前读入内存，而FileInputStream每次都从磁盘读取
     */
    private static void fileCopyWithIOBufferStream(String filePath) {
        try (
//                扩大内存缓冲区可以更快
                var in = new BufferedInputStream(new FileInputStream(filePath), 16384);
                var out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\chenxing\\Desktop\\bufStreamCopy.txt"), 16384)
        ){
            byte[] buffer = new byte[2048];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void fileCopyWithIOStream(String filePath) {
        try (
                var in = new FileInputStream(filePath);
                var out = new FileOutputStream("C:\\Users\\chenxing\\Desktop\\streamCopy.txt")
        ){
            byte[] buffer = new byte[2048];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
