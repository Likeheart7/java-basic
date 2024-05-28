package IO;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * -XX:+PrintCommandLineFlags : 打印虚拟机信息
 * -XX:+UseSerialGC：使用其他的垃圾回收器
 */
public class FilesDemo {
    public static void main(String[] args) {
//        需要回收资源
//        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\chenxing\\Desktop\\node.txt"), StandardCharsets.UTF_8)) {
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(Charset.defaultCharset());
        System.out.println(FileSystems.getDefault());
        try {
//            将所有数据读到一个List
            Files.readAllLines(Paths.get("C:\\Users\\chenxing\\Desktop\\node.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
