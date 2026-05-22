package com.wpj.controller;

import com.wpj.pojo.User;
import com.wpj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    //初始化方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("login".equals(method)){
            //登录
            login(req,resp);
        }else if("logout".equals(method)){
            //退出登录
            logout(req,resp);
        }else {
            login(req,resp);
        }
    }

    //登录
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        //1.获取请求中的参数
        String uname = req.getParameter("uname");
        System.out.println(uname);
        String password = req.getParameter("password");
        System.out.println(password);
        User u = userService.login(uname,password);
        System.out.println(u);
        //3.根据业务层返回结果判断响应的内容
        if(u != null){
            //1.把用户的信息设置到session作用域中
            req.getSession().setAttribute("u",u);
            resp.sendRedirect(req.getContextPath()+"/bookServlet?methodName=bookList");
        }else{
            resp.sendRedirect(req.getContextPath()+"/login.jsp?flag=false");
        }
    }

    //退出登录
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        session.removeAttribute("user");

        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

}
