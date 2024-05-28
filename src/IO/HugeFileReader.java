package IO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;

public class HugeFileReader {
    public static void main(String[] args) throws IOException {
        var filePath = System.getProperties().get("user.home").toString() + "/desktop/file.txt";
//        readFileWithReadAllLines(filePath);
        readFileWithLines(filePath);    // 因为lines方法返回的是Stream，所以可以操作，也可以自己用InputStream操作
    }

    /**
     * 通过流读取文件
     *
     * @param filePath 文件路径
     */
    private static void readFileWithLines(String filePath) {
        try {
            var lines = Files.lines(Paths.get(filePath));
            int cnt = 1;
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过readAllLines方法读取，在限制128M最大堆大小的情况下会直接堆溢出。
     *
     * @param filePath 文件路径
     */
    private static void readFileWithReadAllLines(String filePath) {
        try {
            var lines = Files.readAllLines(Paths.get(filePath));
            System.out.println(lines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

        }
    }

    /**
     * 在桌面创建file.txt文件，通过uuid写入1GB内容
     */
    private static void createFile(String filePath) {


        var file = new File(filePath);
        try (var bfIn = new BufferedOutputStream(new FileOutputStream(file, true), 1024)) {
            if (!file.exists()) {
                var createResp = file.createNewFile();
                if (!createResp) {
                    System.out.println("create file failed, program determination(ㄒoㄒ)");
                    return;
                }
            }
//        写入1GB文件
            for (int i = 0; i < 1_073_741_824 / 37; i++) {
                bfIn.write((UUID.randomUUID() + "\n").getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
