package IO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\chenxing\\Desktop\\demo.txt");
        try (var output = new FileOutputStream(file)) {
            output.write("你好\n".getBytes(StandardCharsets.UTF_8));
            output.write("这里是\n".getBytes(StandardCharsets.UTF_8));
            output.write("编程笔记\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
