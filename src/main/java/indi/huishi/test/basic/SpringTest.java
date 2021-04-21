package indi.huishi.test.basic;//package indi.huishi.test;
//

import indi.huishi.pojo.Page;
import indi.huishi.pojo.Student;
import indi.huishi.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 1.测试spring配置是否正确
 * 2.测试MyBatis配置：见MybatisTest
 * 3.测试整合Spring + MyBatis配置
 * @author Huishi
 */
public class SpringTest {
//    @Test
//    public void test1(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student = (Student) context.getBean("student");
//        System.out.println(student);
//    }

    /**
     * 3.测试整合Spring + MyBatis配置
     */
    @Test
    public void test2(){
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        StudentService as = (StudentService) ac.getBean("studentService");
        System.out.println(as);
        Page<Student> show = as.show(1, 3);
        System.out.println(show);
    }

}

