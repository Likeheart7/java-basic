package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncr {
    private static int count = 1;
    private static AtomicInteger atomicCount = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
//        incrPrimitive();
        incrAtomic();
    }

//    存在竞争导致的结果出错问题
    private static void incrPrimitive() {
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
        }, "subThread-1");
        final Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
        }, "subThread-2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    private static void incrAtomic(){
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                atomicCount.incrementAndGet();
            }
        }, "subThread-1");
        final Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                    atomicCount.incrementAndGet();
            }
        }, "subThread-2");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicCount.get());
    }
}
