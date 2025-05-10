package launch;

/*
 * 一个简单Java程序的启动过程，版本为JDK17(JDK9才引入统一日志参数 -Xlog:all)
 * 命令行启动 java -Xlog:all=trace launch.HelloWorld > log.log
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world.");
    }
}
