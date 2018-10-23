package csk.memcatchd;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * memcatchd 操作实例
 */
public class Memcatchd {
    static MemcachedClient mcc;

    /**
     * 打开连接
     *
     * @throws IOException
     */
    @BeforeClass
    public static void init() throws IOException {
        mcc = new MemcachedClient(new InetSocketAddress("192.168.142.171", 11211));
        System.out.println("Connection to server successful.");
    }

    @Test
    public void setTest() throws ExecutionException, InterruptedException {
        Future fo = mcc.set("myKey", 900, "My values");
        // 查看存储状态
        System.out.println("set status:" + fo.get());
        // 输出值
        System.out.println("myKey value in cache - " + mcc.get("myKey"));

    }

    @Test
    public void addTest() throws ExecutionException, InterruptedException {
        OperationFuture<Boolean> future = mcc.set("key", 1000, "value");
        System.out.println("storing result -->" + future.get());
        Object val = mcc.get("key");
        System.out.println("get a value of key -->" + val);
        OperationFuture<Boolean> future1 = mcc.add("key2", 1000, "value2");
        System.out.println("storing result -->" + future1.get());
        Object key2 = mcc.get("key2");
        System.out.println("get a value of key2 -->" + key2);
        //已存在会存储失败;
        OperationFuture<Boolean> future2 = mcc.add("key2", 1000, "value22");
        System.out.println("storing result -->" + future2.get());
    }

    @Test
    public void replaceTest() throws ExecutionException, InterruptedException {
        OperationFuture<Boolean> rs = mcc.set("rp", 1000, "I love everything you did.");
        System.out.println("storing result -->" + rs.get());
        System.out.println("get a value of rp -->" + mcc.get("rp"));
        OperationFuture<Boolean> rp = mcc.replace("rp", 2000, "everything you did is my favourite");
        System.out.println("storing result -->" + rp.get());
        System.out.println("get a new value of rp -->" + mcc.get("rp"));
    }

    @Test
    public void appendTest() throws ExecutionException, InterruptedException {
        OperationFuture<Boolean> ap = mcc.set("ap", 1000, "a value which will be appended by other value");
        System.out.println("storing result -->" + ap.get());
        OperationFuture<Boolean> future = mcc.append("ap", " which is nice");
        System.out.println("storing result -->" + future.get());
        System.out.println("get a new value of rp -->" + mcc.get("ap"));
        OperationFuture<Boolean> prepend = mcc.prepend("ap", "I set a ");
        System.out.println("storing result -->" + prepend.get());
        System.out.println("again get a new value of rp -->" + mcc.get("ap"));
    }

    /**
     * Memcached CAS（Check-And-Set 或 Compare-And-Swap） 命令用于执行一个"检查并设置"的操作
     * 它仅在当前客户端最后一次取值后，该key 对应的值没有被其他客户端修改的情况下， 才能够将值写入。
     * 检查是通过cas_token参数进行的， 这个参数是Memcach指定给已经存在的元素的一个唯一的64位值。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void checkAndSetTest() throws ExecutionException, InterruptedException {
        OperationFuture<Boolean> set = mcc.set("ck", 1000, "value");
        System.out.println("storing result -->" + set.get());
        System.out.println("get a new value of ck -->" + mcc.get("ck"));
        CASValue<Object> ck = mcc.gets("ck");
        System.out.println("ck token-->" + ck);
        //update a value with ck token
        CASResponse cas = mcc.cas("ck", ck.getCas(), 1000, "new value");
        System.out.println("storing result -->" + cas);
        System.out.println("get a new value of ck -->" + mcc.get("ck"));
    }

    @Test
    public void deleteTest() {
        OperationFuture<Boolean> del = mcc.set("del", 1000, "12121");
        System.out.println("storing result -->" + del);
        System.out.println("get a value of del -->" + mcc.get("del"));
        OperationFuture<Boolean> del1 = mcc.delete("del");
        System.out.println("deleting result -->" + del1);
        System.out.println("again get a value of del -->" + mcc.get("del"));
    }

    @Test
    public void increaseAndDecreaseTest() {
        OperationFuture<Boolean> increaseAndDecrease = mcc.set("increaseAndDecrease", 1000, "121");
        System.out.println("storing result -->" + increaseAndDecrease);
        long increase = mcc.incr("increaseAndDecrease", 123);
        System.out.println("operating result -->" + increase);
        System.out.println("get a value of del -->" + mcc.get("increaseAndDecrease"));
        long decrease1 = mcc.decr("increaseAndDecrease", 115);
        System.out.println("operating result -->" + decrease1);
        System.out.println("again get a value of del -->" + mcc.get("increaseAndDecrease"));
    }

    @Test
    public void statisticsTest() {
        stats("items");
        System.out.println("-----------");
        //get keys in 100
        stats("cachedump 1 100");
    }

    private void stats(String item) {
        Map<SocketAddress, Map<String, String>> items = mcc.getStats(item);
        for (Map<String, String> entry : items.values()) {
            for (Map.Entry<String, String> e : entry.entrySet()) {
                System.out.println(String.format("key=%s  --> value=%s", e.getKey(), e.getValue()));
            }
        }
    }

    /**
     * 关闭连接
     */
    @AfterClass
    public static void destroy() {
        mcc.shutdown();
        System.out.println("Connection shutdown");
    }
}
