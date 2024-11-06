package IOFromBook.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class FileEx {
    public static void main(String[] args) throws IOException {
//        File file = new File("./out");
////        boolean result = file.mkdir();  //   创建文件夹
//        boolean result = file.exists();
//        System.out.println(result);
//        listJavaFile(new File("."));
        RandomAccessFile file = new RandomAccessFile(".gitignore", "r");
        System.out.println(file.getFD());
        file.close();
    }

    /**
     * 列出所有子文件中的java文件
     */
    private static void listJavaFile(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach(FileEx::listJavaFile);
        } else {
            if (file.getPath().endsWith(".java")) {
                System.out.println(file.getPath());
            }
        }
    }
}
