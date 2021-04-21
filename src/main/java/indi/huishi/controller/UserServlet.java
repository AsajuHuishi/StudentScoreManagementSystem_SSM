//package indi.huishi.controller;
//
//import com.google.gson.Gson;
//import indi.huishi.pojo.User;
//import indi.huishi.service.UserService;
//import indi.huishi.service.impl.UserServiceImpl;
//import indi.huishi.utils.WebUtils;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
//
///**
// * 用户 注册登录两功能
// */
//public class UserServlet extends BaseServlet {
//    private UserService userService = new UserServiceImpl();
//
//    // 原来登录和注册 的代码copy过来
//    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //获取请求参数
////        String username = req.getParameter("username");
////        String password = req.getParameter("password");
//        // 使用BeanUtils方法
//        User userBean = WebUtils.copyParamToBean(req.getParameterMap(), new User());//和User set方法有关
//        // 查看
////        Map<String, String[]> parameterMap = req.getParameterMap();
////        for(Map.Entry<String, String[]> entry: parameterMap.entrySet()){
////            System.out.println(entry.getKey()+" : "+ Arrays.asList(entry.getValue()));
////            //这是所有请求传过来的参数
//////            username : [huishi]
//////            password : [123456]
//////            action : [login]
////        }
//        //检查 用户名和密码是否存在
//        User user = userService.login(userBean);
//        if (user==null){
//            //用户不存在
////            System.out.println("用户不存在");
//            // 把错误信息 和回显表单信息 保存
//            req.setAttribute("msg","用户名或密码错误");
//            req.setAttribute("username",userBean.getUsername());
//            //返回登录界面
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }else{
//            //跳转到登录成功页面
//            // 保存用户成功登陆的信息在session域 因为后面都会用到
//            req.getSession().setAttribute("user",user);
//            System.out.println("登录成功");
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        }
//    }
//    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 获取session中的验证码
//        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        // 删除session中的验证码
//        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//        //1.获取请求的参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
////        System.out.println(username+password+email+code);
//        //判断验证码 忽略大小写
//        if(token!=null && token.equalsIgnoreCase(code)) {
//            //如果验证码正确 检查用户名是否可用
//            if (userService.existsUsername(username)) {
//                //如果用户名已经存在，返回注册页面
////                System.out.println("用户名已经存在");
//                // 回显
//                req.setAttribute("msgs","用户名已经存在");
//                req.setAttribute("username",username);
//                req.setAttribute("email",email);
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
//                requestDispatcher.forward(req, resp);
//            } else {
//                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
//                // 保存用户成功登陆的信息在session域 因为后面都会用到
//                req.getSession().setAttribute("user",user);
//                //用户名不存在，将用户名 密码 邮箱 保存到数据库
//                userService.registerUser(user);
//                //跳转到 注册成功界面
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist_success.jsp");
//                requestDispatcher.forward(req, resp);
//            }
//        }else{
//            //验证码错误
//            System.out.println("验证码错误"+code);
//            // 把回显信息保存到request域 回显用户名邮箱
//            req.setAttribute("msgs","验证码错误");
//            req.setAttribute("username",username);
//            req.setAttribute("email",email);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
//            requestDispatcher.forward(req, resp);
//        }
//    }
//
//    /**
//     * 注销
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //1.销毁Session中用户登录的信息
//        req.getSession().invalidate();
//        //2.重定向到首页
//        resp.sendRedirect(req.getContextPath()); //在login_success_menu.jsp和首页中注销
//    }
//
//
//    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("请求ajaxExistUsername");
//        // 获取请求参数
//        String username = req.getParameter("username");
//        // 调用userService的方法查看用户名是否存在
//        boolean flag = userService.existsUsername(username);
//        // 保存成json返回响应
//        Map<String,Boolean> map = new HashMap<>();
//        map.put("exist",flag);
//        resp.getWriter().write(new Gson().toJson(map));
//    }
//}
