package csk.bases.junit;

import static org.junit.Assume.assumeNoException;

import org.junit.*;

import java.util.Random;

/**
 * junit测试执行顺序： beforeClass FileIo 构造函数 before test after afterClass
 */
public class Unit {

    public Unit() {
        System.out.println("FileIo");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @Test
    public void test() {
        int x = 4;
        boolean result = (x & (x - 1)) == 0;
        System.out.println("result=" + result);
        Assume.assumeNoException("异常", null);

    }

    @Test
    public void  ts(){
        //打印 30到50之间的随机数
        int min = 30;
        int max = 50;
        for(int i=0;i<10;i++){
            System.out.println(new Random().nextInt(max-min)+min);
        }
    }
}
