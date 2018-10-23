package csk.redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

public class Redis {
    private static Jedis jedis;

    @BeforeClass
    public static void init() {
        jedis = new Jedis("192.168.142.171");
        System.out.println("connection to redis server successful");
    }

    @AfterClass
    public static void destroy() {
        jedis.close();
        System.out.println("closed connection");
    }

    @Test
    public void setAndGetTest() {
        String set = jedis.set("setKey", "myValue");
        System.out.println(set);
        String val = jedis.get("setKey");
        System.out.println(val);
    }

    @Test
    public void listTest() {
        Long lash = jedis.lpush("list", "hello", "world", "yes", "no");
        System.out.println(lash);
        List<String> list = jedis.lrange("list", 0, 10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item of list: " + list.get(i));
        }
    }

    @Test
    public void mapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "first Value");
        map.put("two", "second");
        String rs = jedis.hmset("dictionary", map);
        System.out.println("storing result -->" + rs);
        Long updatingResult = jedis.hset("dictionary", "one", "NO.1");
        System.out.println(updatingResult);
        Map<String, String> dictionary = jedis.hgetAll("dictionary");
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(String.format("key=%s --> value=%s", entry.getKey(), entry.getValue()));
        }
        System.out.println("over");
    }

    @Test
    public void getAllKeys() {
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }
}
