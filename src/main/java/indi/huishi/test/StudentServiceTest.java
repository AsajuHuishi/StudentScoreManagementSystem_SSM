package indi.huishi.test;

import indi.huishi.pojo.Page;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import indi.huishi.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试studentService
 * @Author: Huishi
 * @Date: 2021/4/17 23:30
 */
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) context.getBean("studentService");
        List<Statistics> statistics = service.statistics();
        System.out.println(statistics);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService service = (StudentService) context.getBean("studentService");
        Page<Student> page = service.show(1, 3);
        System.out.println(page);
    }
}
