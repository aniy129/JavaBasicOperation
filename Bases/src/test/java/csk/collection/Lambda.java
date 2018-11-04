package csk.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Lambda {
    List<Person> list = null;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add(new Person("小华", 12, new Date(1989, 12, 23)));
        list.add(new Person("小红", 13, new Date(1990, 2, 23)));
        list.add(new Person("小明", 14, new Date(1992, 5, 7)));
        list.add(new Person("李丽", 15, new Date(1995, 4, 6)));
    }

    @Test
    public void test() {
        list.forEach(c -> System.out.println(c.getName()));
    }

    @Test
    public void filter() {
        System.out.println("filter:");
        list.stream().filter(c -> c.name.startsWith("小")).forEach(c -> System.out.println(c.toString()));
        System.out.println("skip limit :");
        list.stream().skip(1).limit(2).forEach(c -> System.out.println(c.toString()));
        System.out.println("filter:");
        list.stream().filter(c -> c.no > 12 && c.no <= 14).forEach(c -> System.out.println(c.toString()));
    }

    @Test
    public void func() {
        Function<String, String> fun = (x) -> x + "yes";
        String rs = fun.apply("hi ");
        System.out.println(rs);
        fun = this::getStr;
        System.out.println(fun.apply("new words"));
        System.out.println(myFun(this::getStr, "大家好"));
        System.out.println(myFun(x -> x + ": --> :", "大家好"));
        System.out.println(myFunc(c -> c + " oh yeah", "my gad"));
    }

    public String getStr(String str) {
        return "omg hi " + str;
    }

    /**
     * @Author: 2018/10/25 17:03
     * @methodParameters: [fun, str]
     * @methodReturnType: java.lang.String
     * @Description: java 8 已支持通过lambda方式传递函数指针类型参数
     */
    public String myFun(Function<String, String> fun, String str) {
        System.out.println(fun.andThen(c -> c + "12"));
        System.out.println(fun.compose(c -> c.toString() + "13"));
        return fun.apply(str) + " finished";
    }

    public String myFunc(IFun fun, String str) {
        return fun.str(str) + " finished";
    }


    @Test
    public void funTest() {
        Person person = person(c -> {
            c.setName(c.getName() + "nono");
            return c;
        }, new Person("小华", 12, new Date(1989, 12, 23)));
        System.out.println("最终参数");
        System.out.println(person);
    }

    /**
     * @Author: 2018/10/25 20:41
     * @methodParameters:
     * @methodReturnType:
     * @Description:
     * Function 三个方法
     * 1)compose 可在执行前修改参数
     * 2)apply 直接执行
     * 3)andThen 执行后可修改结果
     */
    public Person person(Function<Person, Person> fun, Person p) {
        Function<Object, Person> compose = fun.compose(c -> {
            System.out.println(c.getClass().getName());
            Person c1 = (Person) c;
            c1.setName(c1.getName() + "yes");
            System.out.println(c1);
            return c1;
        });
        System.out.println("执行前参数");
        System.out.println(compose.apply(p));

        fun.apply(p);
        Function<Person, Person> andThen = fun.andThen(c -> {
            c.setName(c.getName() + "omg");
            System.out.println(c);
            return c;
        });
        System.out.println("执行后参数");
        System.out.println(andThen.apply(p));
        return p;
    }
}

class Person {
    String name;
    int no;

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", no=" + no +
                ", birthday=" + birthday +
                '}';
    }

    Date birthday;

    public Person(String name, int no, Date birthday) {
        this.name = name;
        this.no = no;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

@FunctionalInterface
interface IFun {
    String str(String string);
}