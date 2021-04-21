package indi.huishi.handler;

/**
 * @Author: Huishi
 * @Date: 2021/4/20 1:45
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghan
 * @description 处理异常的拦截类
 * @date 2021/1/22
 **/
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @description //错误异常处理
     * @param request : 出错的 URL
     *        e : 异常
     * @return ModelAndView
     * @date 2021/1/22
     * ExceptionHandler //标识异常的注解
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(), e);
        ModelAndView mv = new ModelAndView();
        //放行加了状态码的异常
        if (e instanceof HttpRequestMethodNotSupportedException) {
            mv.addObject("url", request.getRequestURI());
            mv.addObject("exception", e);
            mv.setViewName("error/405");
            return mv;
        }

        if (e instanceof MissingServletRequestParameterException) {
            mv.addObject("url", request.getRequestURI());
            mv.addObject("exception", e);
            mv.setViewName("error/400");
            return mv;
        }

        if (e instanceof NoHandlerFoundException) {
            mv.addObject("url", request.getRequestURI());
            mv.addObject("exception", e);
            mv.setViewName("error/404");
            return mv;
        }


        mv.addObject("url", request.getRequestURI());
        mv.addObject("exception", e);
        mv.setViewName("error/500");
        return mv;
    }
}