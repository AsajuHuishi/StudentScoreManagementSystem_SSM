package indi.huishi.test;

import indi.huishi.dao.StudentDao;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * StudentDao方法测试
 * @author Huishi
 */
public class StudentDaoTest {

    private StudentDao studentDao;
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        SqlSession session = factory.openSession();
        studentDao = session.getMapper(StudentDao.class);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student(6,"105","zxy",Float.parseFloat("88"),2);
        int res = studentDao.addStudent(student);
        System.out.println(res);
    }

    @Test
    public void testShowAll() {
        List<Student> list = studentDao.showAll(3,3);
        System.out.println(list);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(2,"101","杨帆",Float.parseFloat("88"),2);
        studentDao.updateStudent(student);
    }

    @Test
    public void testQueryByName() {
        List<Student> list = studentDao.queryByName("zxy");
        System.out.println(list);
    }

    @Test
    public void testDeleteStudent() {
        int i = studentDao.deleteStudent(2);
        System.out.println(i);
    }

    @Test
    public void testStatistics(){
        List<Statistics> statistics = studentDao.getStatistics();
        System.out.println(statistics);
    }

    @Test
    public void getTotalCount(){
        Integer count = studentDao.getTotalCount();
        System.out.println(count);
    }
}
