package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\chenxing\\Desktop\\node.txt");
        try (FileInputStream input = new FileInputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = input.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
