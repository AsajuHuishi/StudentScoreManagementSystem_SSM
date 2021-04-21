package indi.huishi.dao;

import indi.huishi.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户注册+登录
 * @author Huishi
 */
@Repository
public interface UserDao {
    /**
     * 根据用户名查询该用户是否存在（注册时判断）
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    User queryUserByUsername(String username);

    /**
     * 根据用户和密码查询该用户是否存在（登录时判断）
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 保存用户信息（注册）
     * @param user
     * @return
     */
    @Select("insert into user(username,password,email) values(#{username}, #{password}, #{email})")
    void saveUser(User user);

}
