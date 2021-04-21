package indi.huishi.service.impl;

import indi.huishi.dao.UserDao;
import indi.huishi.pojo.User;
import indi.huishi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现用户的注册和登陆功能
 * @author Huishi
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 注册
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User login(User user) {
        //判断用户名和密码是否在数据库中
        User user1 = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return user1;
    }

    /**
     * 判断用户名是否已经存在
     * @param username
     * @return返回true表示用户名已经存在
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean existsUsername(String username) {
        //根据用户名查询
        return userDao.queryUserByUsername(username)!=null?true:false;

    }
}
