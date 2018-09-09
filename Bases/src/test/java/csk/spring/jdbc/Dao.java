package csk.spring.jdbc;

import javax.sql.DataSource;
import java.util.List;

public interface Dao {

    void setDataSource(DataSource ds);

    void create(String name, Integer age);

    Student getStudent(Integer id);

    List<Student> listStudents();

    void delete(Integer id);

    void update(Integer id, Integer age);

    Student getStudentByProduce(Integer id);
}
