package IO;

import org.apache.commons.lang.time.StopWatch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class BufferedReadWriteFile {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\chenxing\\Desktop\\demo.txt");
//        bufferedWrite(file);
        bufferedRead(file);

    }

    private static void bufferedWrite(File file) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try (var output = new BufferedOutputStream(new FileOutputStream(file))) {
            var str = UUID.randomUUID().toString().replace("-", "") + "\n"; // 32个字符，32个字节
            for (int i = 0; i < 10_0000; i++) { // 应当3M左右
                output.write(str.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime());    // 用时114ms, FileOutputStream约865ms
    }

    private static void bufferedRead(File file) {
        try (var input = new BufferedInputStream(new FileInputStream(file), 8192)) {
            int len;
//            var buf = new byte[3];    // 如果使用字节流读取，可能导致一个字符被拆分，出现乱码的情况
            var buf = new byte[1024];
            while((len = input.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
