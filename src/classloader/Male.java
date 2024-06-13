package classloader;

public class Male extends Person {
    static {
        System.out.println("Male 的静态代码块");
    }

    {
        System.out.println("Male 的实例代码块");
    }

    public Male() {
        System.out.println("Male 的构造器");
    }

}
