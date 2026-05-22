package com.wpj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class BMFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("调用init。。。。。。");
    }

    /**
     * 设置web项目编码格式
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("销毁destory。。。。。。");
    }
}
/**
 *当有多个Filter都对同一个请求路径进行拦截的时候，xml配置和注解中Filter执行顺序不一样！！！
 *1. xml配置方式的Filter，执行顺序按照在web.xml中配置的顺序，从上到下执行；
 *2. 注解开发的Filter，执行的顺序是按照实现类的字母表顺序依次执行！
 */