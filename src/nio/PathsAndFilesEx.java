package nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * {@link Paths} 和 {@link Files}
 */
public class PathsAndFilesEx {
    public static void main(String[] args) {
        Path rootPath = Paths.get("./src/nio");

        try {
            // 遍历文件夹并打印
            Files.walkFileTree(rootPath, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String filePath = file.toAbsolutePath().toString();

                    System.out.println("file found at path: " + file.toAbsolutePath());
//                    if (filePath.endsWith(".txt")) return FileVisitResult.TERMINATE; // 返回TERMINATE可以提前结束遍历。
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
