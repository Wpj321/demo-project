package com.wpj.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 自定义登录验证的过滤器,只需要过滤访问系统内部页面的请求
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //从session中获取登录信息
        HttpSession session = req.getSession();
        Object u = session.getAttribute("u");
        String URI = req.getRequestURI();
        System.out.println("URI = " + URI);
        StringBuffer URL = req.getRequestURL();
        System.out.println("URL = " + URL);
        //访问路径为登录时，通过验证
        if (URI.indexOf("login.jsp")>0 || URI.indexOf("userServlet")>0 ){
            filterChain.doFilter(req,resp);
        }else {
            //判断用户信息是否正确或是否登录
            if (u == null){
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }else {
                //不为null则是登陆成功或已经登陆过了，让请求通过
                filterChain.doFilter(req,resp);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
