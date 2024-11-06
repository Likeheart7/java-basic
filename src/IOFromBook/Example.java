package IOFromBook;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Example {
    public static void main(String[] args) {
        Path path = Paths.get("/");
        System.out.println(path.toAbsolutePath());
    }
}
