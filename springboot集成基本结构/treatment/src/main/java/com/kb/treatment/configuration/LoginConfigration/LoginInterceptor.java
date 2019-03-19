package com.kb.treatment.configuration.LoginConfigration;

import com.kb.treatment.configuration.ExceptionConfiguration.KbException;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *  登录拦截全局配置
 * **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     *   进api访问之前
     * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       //在此处判断是否登录
        throw new KbException("没有登录");
//        return true;
    }


    /**
     *  进api访问之后，且在跳转到jsp之前
     * */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     *   postHandle 再Controller中的方法处理之后调用，但是在视图被渲染之前。
     * */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    /**
     * afterCompletion 在 渲染试图之后调用。
     * */
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


    }

}
