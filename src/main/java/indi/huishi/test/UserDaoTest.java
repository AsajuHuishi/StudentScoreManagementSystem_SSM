package indi.huishi.test;
//
//import indi.huishi.dao.UserDao;
//import indi.huishi.pojo.User;
//import org.junit.Test;
//
//public class UserDaoTest {
//    UserDao userDao = new UserDaoImpl();
//
//    @Test
//    public void queryUserByUsername(){
//        User user = userDao.queryUserByUsername("admin");
//        System.out.println(user.toString());
//        if(user==null){
//            System.out.println("用户名不存在");
//        }else{
//            System.out.println("用户名已经存在");
//        }
//    }
//    @Test
//    public void queryUserByUsernameAndPassword(){
//        User user = userDao.queryUserByUsernameAndPassword("admin","admin");
//        System.out.println(user.toString());
//        if(user==null){
//            System.out.println("用户名密码不正确");
//        }else{
//            System.out.println("查询成功");
//        }
//    }
//
//    @Test
//    public void saveUser(){
//        //新建一个用户的信息，保存到数据库中
//        User user = new User(1,"zengxinyang","970319","939276378@qq.com");
//        int result = userDao.saveUser(user);
//        System.out.println(result==1?"添加成功":"添加失败");
//    }
//}

import indi.huishi.dao.UserDao;
import indi.huishi.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * UserDao方法测试
 * @author Huishi
 */
public class UserDaoTest {

    private UserDao userDao;

    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        SqlSession session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @Test
    public void testAddStudent() {
        User user = userDao.queryUserByUsername("huishi");
        System.out.println(user.toString());
        if(user==null){
            System.out.println("用户名不存在");
        }else{
            System.out.println("用户名已经存在");
        }
    }
    @Test
    public void queryUserByUsernameAndPassword(){
        User user = userDao.queryUserByUsernameAndPassword("apple","123");
        System.out.println(user.toString());
        if(user==null){
            System.out.println("用户名密码不正确");
        }else{
            System.out.println("查询成功");
        }
    }
//
    @Test
    public void saveUser(){
        //新建一个用户的信息，保存到数据库中
        User user = new User(98,"clita","970319","939276378@qq.com");
        userDao.saveUser(user);
    }
}