package Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    // private volatile int a = 0;
    private AtomicInteger a = new AtomicInteger(0);

    public void add() {
        a.incrementAndGet();
    }

    public static void main(String[] args) {
        VolatileTest v = new VolatileTest();

        for(int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for(int j = 0; j < 1000; j++) {
                        v.add();
                    }
                }
            }.start();
        }

        while(Thread.activeCount() > 2) {
            Thread.yield();		// When have work thread, yield the resource
        }

        System.out.println("a is " + v.a);
    }

}
