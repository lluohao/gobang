package com.llhao.gobang.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by llhao on 2017/4/14.
 */
public class EnvironmentInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        Enumeration<String> names  = req.getHeaderNames();
        for (String name = null;names.hasMoreElements();) {
            name = names.nextElement();
            System.out.println(name+":::"+req.getHeader(name));

        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
