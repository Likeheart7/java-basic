package serial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationDemo {
    public static void main(String[] args) {
        // 四个序列化相关方法的执行顺序
        /*
            enter writeReplace()
            enter writeExternal
            enter readExternal
            enter readResolve()
         */
        Person person = new Person();
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("person.ser")))) {
            oos.writeObject(person);
        } catch (IOException e) {
            // pass
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("person.ser")))) {
            Person p = ((Person) ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            // pass
        }
    }
}
