package csk.spring;

import csk.spring.jdbc.JDBCTemplate;
import csk.spring.jdbc.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
    @Test
    public void XmlJdbcTest(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("xmlContext.xml");
        JDBCTemplate JDBCTemplate =
                (JDBCTemplate)context.getBean("JDBCTemplate");

        List<Student>list=new ArrayList<>();
        list.add(new Student(18,"小华",9));
        list.add(new Student(18,"小红",8));
        JDBCTemplate.createMultiIndividualsByStatement(list);

        System.out.println("使用事务添加多个完成");
//        System.out.println("------創建--------" );
//        JDBCTemplate.create("Zara", 11);
//        JDBCTemplate.create("Adi", 2);
//        JDBCTemplate.create("Lining", 15);
        System.out.println("------列表--------" );
        List<Student> students = JDBCTemplate.listStudents();
        for (Student record : students) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.println(", Age : " + record.getAge());
        }
        System.out.println("----更新 ID = 2 -----" );
        JDBCTemplate.update(2, 20);
        System.out.println("----列表 ID = 2 -----" );
        Student student = JDBCTemplate.getStudent(2);
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());

        System.out.println("通过存储过程执行");
        student = JDBCTemplate.getStudentByProduce(2);
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());
    }
}
