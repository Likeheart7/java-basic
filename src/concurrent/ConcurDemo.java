package concurrent;

import java.util.concurrent.CountDownLatch;

public class ConcurDemo {
    private static int cnt = 0;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch ct = new CountDownLatch(2);
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incr();
            }
            ct.countDown();
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incr();
            }
            ct.countDown();
        }).start();
        ct.await();
        System.out.println(cnt);
    }
    private static synchronized void incr() {
        cnt++;
    }
}
