package csk.bases.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    int count = 0;

    /*
     * 基本线程池创建
     * */
    @Test
    public void Test() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new
                ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.execute(() -> {
                        System.out.println(String.format("当前线程Id：%s 线程数：%s 正在执行任务数：%s 已执行任务数：%s",
                                Thread.currentThread().getId(),
                                threadPoolExecutor.getPoolSize(),
                                threadPoolExecutor.getQueue().size(),
                                threadPoolExecutor.getCompletedTaskCount()));
                        add();
                    }
            );
        }

        Thread.sleep(3000);
        threadPoolExecutor.shutdown();
    }

    //内置静态方法创建

    /*
     * 固定容量线程池
     * */
    @Test
    public void FixedCountThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> System.out.println(Thread.currentThread().getId()));
        }
        Thread.sleep(1000);
    }

    /*
     * 单一容量线程池
     * */
    @Test
    public void singleCountThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            service.execute(() ->
                    {
                        System.out.println(Thread.currentThread().getId());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        Thread.sleep(1000);
    }

    /*
     * 缓冲池
     * */
    @Test
    public void cacheThreadPool() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    synchronized void add() {
        count++;
        System.out.println("count=" + count);
    }
}
