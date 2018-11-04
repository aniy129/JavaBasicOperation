package csk.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class DistributeLock {
    CuratorFramework client = null;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        String string = "192.168.142.161:2181,192.168.142.162:2181,192.168.142.163:2181";
        client = CuratorFrameworkFactory.builder()
                .connectString(string)
                .sessionTimeoutMs(15000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
    }

    @After
    public void destroy() {
        client.close();
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            countDownLatch.countDown();
            int finalI = i;
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    InterProcessMutex mutex = new InterProcessMutex(client, "/my/curator/lock");
                    mutex.acquire();
                    int rs = add();
                    log.info(String.format("执行结果%s %s获得了锁", rs, finalI));
                    mutex.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }).start();
        }
        Thread.sleep(10000);
    }

    int x = 0;

    public int add() {
        x++;
        return x;
    }
}
