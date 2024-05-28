package action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static void main(String[] args) throws IOException {
//        System.out.println(System.getProperty("user.dir"));
        String file = "src/action/application.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("age"));
        System.out.println(properties.getProperty("hobby"));
    }
}
