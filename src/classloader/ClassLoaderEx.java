package classloader;

/*
关于Class.forName 和 ClassLoader.LoaderClass 的区别：
1. CLass.forName 会初始化对象，所以对象的静态代码块会被执行，loadClass只会把类加载到JVM，不会初始化
 */
public class ClassLoaderEx {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clazz = Class.forName("classloader.Person");   // 输出  Person 的静态代码块， 执行了Person的静态代码块
//        Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("classloader.Person");    // 不会执行Person的静态代码块
        // forName也可以显示指定是否初始化
//        Class<?> clazz = Class.forName("classloader.Person", false, ClassLoaderEx.class.getClassLoader());  // 不会执行静态代码块
//        System.out.println(clazz);

        // 关于类中各部分的加载顺序
        /*
            Person 的静态代码块
            Male 的静态代码块
            Person 的实例代码块
            Person 的构造器
            Male 的实例代码块
            Male 的构造器
         */
        Male male = new Male();
        System.out.println(Person.getAttribute());  // 构造器给的属性值
    }
}
