package csk.bases.thread;

import csk.bases.thread.ProducerConsumerPattern.Consumer;
import csk.bases.thread.ProducerConsumerPattern.Producer;
import csk.bases.thread.ProducerConsumerPattern.Resources;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    @Test
    public void lambdaThreadTest() throws InterruptedException {
        Thread lambdaThreadTest = new Thread(() -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println("LambdaThreadTest");
            System.out.println("Thread Name=" + Thread.currentThread().getName());
        });
        lambdaThreadTest.setName("lambdaThread");
        lambdaThreadTest.join(1000 * 60);
        lambdaThreadTest.start();
    }

    int classicalValue = 0;

    @Test
    public void classicalThreadTest() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            changeClassicalValue();
        }
        Thread.sleep(2000);
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            System.out.println(String.format("key=%s val=%s", kv.getKey(), kv.getValue()));
        }
    }

    /*
     * 创建返回值的线程
     * 1. 创建 Callable 接口的实现类，并实现 call() 方法，该 call() 方法将作为线程执行体，并且有返回值。
     * 2. 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，该 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值。
     * 3. 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程。
     * 4. 调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值。
     * */
    @Test
    public void FutureAndReturningValueThreadTest() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 100 + 105;
        });
        new Thread(futureTask).run();
        System.out.println(futureTask.get());
    }

    @Test
    public void ProduceAndConsumerTest() throws InterruptedException {
        Resources resources = new Resources(10);
        Producer pr1 = new Producer(resources);
        Producer pr2 = new Producer(resources);
        Producer pr3 = new Producer(resources);
        Thread pro1 = new Thread(pr1);
        Thread pro2 = new Thread(pr2);
        Thread pro3 = new Thread(pr3);
        Thread con1 = new Thread(new Consumer(resources));
        Thread con2 = new Thread(new Consumer(resources));
        Thread con3 = new Thread(new Consumer(resources));

        pro1.start();
        pro2.start();
        pro3.start();
        con1.start();
        con2.start();
        con3.start();
        Thread.sleep(1000 * 10);
        pr1.turnOff();
        pr2.turnOff();
        pr3.turnOff();
        Thread.sleep(3000);
//        pro1.interrupt();
//        pro2.interrupt();
//        pro3.interrupt();
//        con1.interrupt();
//        con2.interrupt();
//        con3.interrupt();
    }

    static Map<Integer, Integer> map = new HashMap<>();

    private void changeClassicalValue() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer x = setAndReturnValue();
                // System.out.println(Thread.currentThread().getId());
                System.out.println(x);
                if (map.containsKey(x)) {
                    map.replace(x, map.get(x) + 1);
                } else {
                    map.put(x, 1);
                }
            }
        }).start();

    }

    synchronized Integer setAndReturnValue() {
        classicalValue++;
        return classicalValue;
    }

    Lock lock = new ReentrantLock();

    Integer setVal() {
        lock.lock();
        try {
            classicalValue++;
            System.out.println(classicalValue);
            return classicalValue;
        } finally {
            lock.unlock();
        }
    }
    @Test
    public void lockTest() throws InterruptedException {
        for (int i=0;i<100;i++){
            new Thread(()->setVal()).start();
        }
        Thread.sleep(1000*3);
        System.out.println(classicalValue);
    }
}
