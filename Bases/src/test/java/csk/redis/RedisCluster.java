package csk.redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RedisCluster {
    static JedisCluster cluster = null;

    @BeforeClass
    public static void init() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.142.191", 6380));
        nodes.add(new HostAndPort("192.168.142.191", 6381));
        nodes.add(new HostAndPort("192.168.142.191", 6382));
        nodes.add(new HostAndPort("192.168.142.191", 6383));
        nodes.add(new HostAndPort("192.168.142.191", 6384));
        nodes.add(new HostAndPort("192.168.142.191", 6385));
        cluster = new JedisCluster(nodes);

    }

    @AfterClass
    public static void destroy() throws IOException {
        cluster.close();
        //集群内部使用池化技术，不需要关闭或只在退出时关闭，否则经常报no reachable node in cluster异常
    }

    @Test
    public void test() {
        cluster.set("today", "2018-10-30 20:07:00");
        String today = cluster.get("today");
        System.out.println(today);
    }

    //    AtomicInteger index = new AtomicInteger();

    CountDownLatch countDownLatch = new CountDownLatch(50);

    @Test
    public void testLock() throws InterruptedException {
        Object obj = new Object();
        for (int i = 0; i < 50; i++)
            new Thread(() -> {
                while (true) {
                    try{
                        if (RedisLock.tryGetDistributedLock(cluster, "123", "123", 1000)) {
                            System.out.println(add(obj));
                            RedisLock.releaseDistributedLock(cluster, "123", "123");
                            break;
                        }
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }).start();
        Thread.sleep(5000);
    }

    Integer index = 0;

    public  Integer add(Object object) {
//        synchronized (object){
        index++;
        return index;
//        }
    }
}

