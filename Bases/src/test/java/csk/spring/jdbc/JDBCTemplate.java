package csk.spring.jdbc;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JDBCTemplate implements Dao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private SimpleJdbcCall jdbcCall;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource).
                withProcedureName("getRecord");
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void create(String name, Integer age) {
        String SQL = "insert into Student (name, age) values (?, ?)";
        jdbcTemplateObject.update(SQL, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
        return;
    }

    public Student getStudent(Integer id) {
        String SQL = "select * from Student where id = ?";
        Student student = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new Mapper());
        return student;
    }

    public List<Student> listStudents() {
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplateObject.query(SQL,
                new Mapper());
        return students;
    }

    public void delete(Integer id) {
        String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
        return;
    }

    public void update(Integer id, Integer age) {
        String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id);
        return;
    }

    public Student getStudentByProduce(Integer id) {
        SqlParameterSource in = new MapSqlParameterSource().
                addValue("in_id", id);
        Map<String, Object> out = jdbcCall.execute(in);
        Student student = new Student();
        student.setId(id);
        student.setName((String) out.get("out_name"));
        student.setAge((Integer) out.get("out_age"));
        return student;
    }

    /**
     * @Author: Aniy on 2018/9/10 15:11
     * @methodParameters: [list]
     * @methodReturnType: void
     * @Description:编程式事务
     */
    @Override
    public void createMultiIndividualsByProgramming(List<Student> list) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            for (Student stu : list) {
                String SQL = "insert into Student (id, name, age) values (?, ?, ?)";
                jdbcTemplateObject.update(SQL, stu.getId(), stu.getName(), stu.getAge());
            }
            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }

    /**
     * @Author: Aniy on 2018/9/10 15:53
     * @methodParameters: [list]
     * @methodReturnType: void
     * @Description: 申明式事务
     */
    @Override
    public void createMultiIndividualsByStatement(List<Student> list) {
        try {
            for (Student stu : list) {
                String SQL = "insert into Student (id, name, age) values (?, ?, ?)";
                jdbcTemplateObject.update(SQL, stu.getId(), stu.getName(), stu.getAge());
            }
        }
        catch (Exception ex){
            throw  ex;
        }
    }


}