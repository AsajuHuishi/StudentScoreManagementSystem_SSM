package indi.huishi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截student/ 判断是否已登录
 * @Author: Huishi
 * @Date: 2021/4/20 20:03
 */
public class ManagerInterceptor implements HandlerInterceptor {
    /**
     * session域中user信息
     */
    private static String userInfo = "user";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(userInfo);
        System.out.println(user);
        if (user == null){
            // 如果用户未登录，跳转到登录页面
            response.sendRedirect(request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath() + '/'+"index.jsp");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
