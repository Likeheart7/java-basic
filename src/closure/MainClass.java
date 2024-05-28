package closure;

import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {
    public static void main(String[] args) {
        // result的生命周期应该在testClosure函数内部，闭包让其生命周期延伸到runnable的执行
        var runnable = testClosure();
        // 在调用该返回值的时候，仍然可以访问已经结束了生命周期的函数testClosure的内部变量count
        runnable.run(); // 1
        runnable.run(); // 2
        runnable.run(); // 3
    }
    public static Runnable testClosure() {
        AtomicInteger count = new AtomicInteger(0);
        Runnable runnable = () -> System.out.println(count.incrementAndGet());
        return runnable;
    }

}
