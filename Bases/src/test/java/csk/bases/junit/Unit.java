package csk.bases.junit;

import static org.junit.Assume.assumeNoException;

import org.junit.*;

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
    public void Test() throws Exception {
        int x = 4;
        boolean result = (x & (x - 1)) == 0;
        System.out.println("result=" + result);
        Assume.assumeNoException("异常", null);

    }

}
