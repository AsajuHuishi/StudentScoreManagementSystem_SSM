package indi.huishi.test;

import indi.huishi.pojo.Page;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import indi.huishi.pojo.User;
import indi.huishi.service.StudentService;
import indi.huishi.service.UserService;
import indi.huishi.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//public class UserServiceTest {
//
//    private UserService userService;
//    @Test
//    public void registerUser(){
////        userService.registerUser(new User(null,"lwt","123456","lwt123456@qq.com"));
//        userService.registerUser(new User(null, "indi/huishi","123456","lwt123456@qq.com"));
//    }
//
//    @Test
//    public void login(){
//        User user1 = userService.login(new User(null,"lwt","123456","lwt123456@qq.com"));
//        System.out.println(user1.toString());
//    }
//
//}
/**
 * 测试UserService
 * @Author: Huishi
 * @Date: 2021/4/17 23:30
 */
public class UserServiceTest {
    @Autowired
    private UserService UserService;
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = (UserService) context.getBean("userService");
//        System.out.println(service);
        boolean existsUsername = service.existsUsername("huishi");
        System.out.println(existsUsername);//true
    }

    /**
     * 模拟登录
     */
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = (UserService) context.getBean("userService");
        User user = service.login(new User(null,"huishi","123456",null));
        System.out.println(user);//User{id=1, username='huishi', password='123456', email='null'}
    }
}