package com.lyl.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个接口不实现也不会报错，因为已经实现了的
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 预处理，controller执行前执行
     * return true就放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false 不放行，可以利用request和response跳转页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器预处理中······");
        return true;
    }
}
