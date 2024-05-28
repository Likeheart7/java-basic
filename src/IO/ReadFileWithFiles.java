package IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ReadFileWithFiles {
    public static void main(String[] args) throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\chenxing\\Desktop\\demo.txt"));
//        for (var line : lines) {
//            System.out.println(line);
//        }
        Stream<String> lines = Files.lines(Paths.get("C:\\\\Users\\\\chenxing\\\\Desktop\\\\demo.txt"));
        lines.forEach(line -> {
            System.out.println(line);
        });
    }
}
