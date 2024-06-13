package classloader;

public class Person {
    static {
        System.out.println("Person 的静态代码块");
    }

    private static String attribute = "显式声明的静态变量值";

    {
        System.out.println("Person 的实例代码块");
    }

    public Person() {
        System.out.println("Person 的构造器");
        attribute = "构造器给的属性值";
    }

    public static String getAttribute() {
        return attribute;
    }
}
