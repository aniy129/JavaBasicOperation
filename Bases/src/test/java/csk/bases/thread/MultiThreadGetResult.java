package csk.bases.thread;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * 获取多个线程执行结果
 * */
public class MultiThreadGetResult {
    StopWatch sw = null;

    @Before
    public void Before() {
        sw = StopWatch.createStarted();
        System.out.println("开始测试……");
    }

    @After
    public void After() {
        System.out.println("测试完成！");
        sw.stop();
        System.out.println(String.format("总共耗时%s毫秒",sw.getTime(TimeUnit.MILLISECONDS)));
    }

    /*
     * 通过线程池执行并手动维护返回结果
     * */
    @Test
    public void getMultiThreadsResultByManualPattern() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> rs = service.submit(() -> (int) Thread.currentThread().getId());
            tasks.add(rs);
        }
        for (int i = 0; i < 10; i++) {
            Future<Integer> valObj = tasks.get(i);
            Integer num = valObj.get();//阻塞方式
            System.out.println(num);
        }
    }

    /*
     * 通过invokeAll函数获取返回结果
     * */
    @Test
    public void getMultiThreadsResultsByInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<Integer> task = () -> (int) Thread.currentThread().getId();
            service.submit(task);
            tasks.add(task);
        }
        List<Future<Integer>> results = service.invokeAll(tasks);
        for (int i = 0; i < 10; i++) {
            Future<Integer> resultObj = results.get(i);
            Integer result = resultObj.get();//阻塞式
            System.out.println(result);
        }
    }

    /*
     * CompletionService内部维护了一个阻塞队列，
     * 只有执行完成的任务结果才会被放入该队列，
     * 这样就确保执行时间较短的任务率先被存入阻塞队列中。
     * */
    @Test
    public void getMultiThreadsResultsByCompletionService() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        LinkedBlockingDeque<Future<Integer>> futures = new LinkedBlockingDeque<>(10);
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(service, futures);
        for (int i = 0; i < 10; i++) {
            executorCompletionService.submit(() -> {
                Thread.sleep(1000);
                return (int) Thread.currentThread().getId();
            });
        }
        for (int i = 0; i < 10; i++) {
            Future<Integer> results = executorCompletionService.take();
            Integer result = results.get();
            System.out.println(result);
        }
    }
}
