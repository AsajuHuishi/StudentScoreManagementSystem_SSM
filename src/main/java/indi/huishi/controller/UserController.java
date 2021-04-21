package indi.huishi.controller;

import indi.huishi.pojo.User;
import indi.huishi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Author: Huishi
 * @Date: 2021/4/20 17:16
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.action")
    protected ModelAndView login(User user, HttpSession session) {
        //检查 用户名和密码是否存在
        User user1 = userService.login(user);
        if (user1 == null) {
            //用户不存在
            ModelAndView view = new ModelAndView("user/login");
            view.addObject("msg", "用户名或密码错误");
            view.addObject("username", user.getUsername());//这里是输入的user,因为user1==null
            // 把错误信息 和回显表单信息 保存
            //返回登录界面
            return view;
        } else {
            //跳转到显示页面
            // 保存用户成功登陆的信息在session域 因为后面都会用到
            session.setAttribute("user", user1);
            System.out.println("登录成功");
            ModelAndView view = new ModelAndView("redirect:/student/show.action?pageNo=1");
            return view;
        }
    }

    /**
     * 注册
     * @param
     * @param session
     */
    @RequestMapping("/register.action")
    protected ModelAndView register(HttpServletRequest req, HttpSession session){
    // 获取session中的验证码
        String token = (String) session.getAttribute("verifyCode");
        // 删除session中的验证码
        session.removeAttribute("verifyCode");
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//            System.out.println(username+password+email+code);
        //判断验证码 忽略大小写
        if(token!=null && token.equalsIgnoreCase(code)) {
            //如果验证码正确 检查用户名是否可用
            if (userService.existsUsername(username)) {
                //如果用户名已经存在，返回注册页面
    //                System.out.println("用户名已经存在");
                // 回显
                ModelAndView view = new ModelAndView("user/regist");
                view.addObject("msgs","用户名已经存在");
                view.addObject("username",username);
                view.addObject("email",email);
                return view;
            } else {
                // 用户名不存在，将用户名 密码 邮箱 保存到数据库
                User user = new User(null, username, password, email);
                // 保存用户成功登陆的信息在session域 因为后面都会用到
                session.setAttribute("user",user);
                userService.registerUser(user);
                //跳转到 注册成功界面
                //跳转到显示页面
                // 保存用户成功登陆的信息在session域 因为后面都会用到
                System.out.println("注册成功");
                ModelAndView view = new ModelAndView("redirect:/student/show.action?pageNo=1");
                return view;
            }
        }else {
            //验证码错误
            System.out.println("验证码错误" + code);
            // 把回显信息保存到request域 回显用户名邮箱
            ModelAndView view = new ModelAndView("user/regist");
            view.addObject("msgs","用户名已经存在");
            view.addObject("username",username);
            view.addObject("email",email);
            return view;
        }
    }

    /**
     * 注销
     * @param session
     */
    @RequestMapping("/logout.action")
    protected ModelAndView logout(HttpSession session){
        //1.销毁Session中用户登录的信息
        session.invalidate();
        //2.重定向到首页
        return new ModelAndView("redirect:/index.jsp");
    }
}
