package serial;

import java.io.*;

public class Person implements Externalizable {
    // Externalizable提供的在序列化时调用的方法
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("enter writeExternal");
    }

    // Externalizable提供的在反序列化时调用的方法
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("enter readExternal");
    }

    // Serializable接口会处理的两个方法，也是在序列化或反序列化过程中调用
    private Object readResolve() throws ObjectStreamException {
        System.out.println("enter readResolve()");
        return this;
    }

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("enter writeReplace()");
        return this;
    }

    @Override
    public String toString() {
        return "this is a Person";
    }
}
