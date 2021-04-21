package indi.huishi.test.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试springmvc配置文件
 * @Author: Huishi
 * @Date: 2021/4/17 23:43
 */
@Controller

public class SpringMVCTest {
    /**
     * http://localhost:8080/shizuo/index?name=as 测试
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        ModelAndView modelAndView = new ModelAndView("test/test");
        modelAndView.addObject("msg", name);
        return modelAndView;
    }
}
