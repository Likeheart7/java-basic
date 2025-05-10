package IO;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class FileCount {
    public static void main(String[] args) throws IOException {
        String rootPath = "D:\\programming\\tool\\netcat";
        withFile(rootPath);
        fileWalker(rootPath); // 数量大时可以快几倍
    }

    private static void withFile(String rootPath) {
        AtomicInteger count = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        file(rootPath, count);
        long end = System.currentTimeMillis();
        System.out.println("普通File递归，总计" + count.get() + "个文件, 用时：" + (end - start) + " ms");
    }

    private static void file(String rootPath, AtomicInteger count) {
        File root = new File(rootPath);
        File[] files = root.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(file -> {
                if (file.isDirectory()) {
                    file(file.getAbsolutePath(), count);
                } else {
                    count.getAndIncrement();
                }
            });
        }
    }

    private static void fileWalker(String rootPath) throws IOException {
        AtomicInteger count = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        Files.walkFileTree(Paths.get(rootPath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file.getFileName());
                count.getAndIncrement();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("FileWalker, 总计 " + count.get() + "个文件，用时：" + (end - start) + " ms");
    }
}
