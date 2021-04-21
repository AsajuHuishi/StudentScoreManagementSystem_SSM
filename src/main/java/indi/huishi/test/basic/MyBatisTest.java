package indi.huishi.test.basic;


import indi.huishi.dao.StudentDao;
import indi.huishi.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 2.测试Mybatis配置： 注释掉applicationContext和Mybatis相关的内容
 * 使用SqlMapConfig.xml
 * @author Huishi
 */
public class MyBatisTest {

    @Test
    public void test() throws IOException {
        //1.读取配置文件,连接数据库的信息
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
//        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建dao接口代理对象
//        IUserDao iUserDao = session.getMapper(IUserDao.class);
        StudentDao studentDao = session.getMapper(StudentDao.class);
        //3.使用工厂创建dao对象
//        IUserDao iUserDao = new IUserDaoImpl(factory);
        //4.使用代理对象执行方法
        List<Student> list = studentDao.showAll(0,2);
        System.out.println(list);
//        List<Users> usersList = iUserDao.findAll();
//        for(Users users:usersList){
//            System.out.println(users);
//        }
        //5.释放资源
//        session.close();
        in.close();
    }
}
