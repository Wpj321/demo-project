package com.wpj.controller;

import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.service.BookService;
import com.wpj.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookServlet")
public class BooksServlet extends HttpServlet {
    @Autowired
    private BookService bookService;

    //初始化方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String methodName=request.getParameter("methodName");
        if("getBookList".equals(methodName)){
            try {
                getBookList(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }else if ("toAdd".equals(methodName)){
//            toAdd(request,response);
//        }else if ("toUpdate".equals(methodName)){
//            toUpdate(request,response);
//        }else if ("updateBook".equals(methodName)){
//            updateBook(request,response);
//        }else if ("deleteBook".equals(methodName)){
//            DeleteBookByid(request,response);
//        }else if ("addBook".equals(methodName)){
//            addBook(request,response);
        }else {
            try {
                getBookList(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private void DeleteBookByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String bid = request.getParameter("bid");
//        BookService bk =new BookServiceImpl();
//        boolean flag = bk.deleteById(bid);
//        if (flag){
//            response.sendRedirect(request.getContextPath()+"/BooksServlet");
//        }
//    }
//
//    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        String bname = request.getParameter("bname");
//        String price = request.getParameter("price");
//        String author = request.getParameter("author");
//        String publishdate = request.getParameter("publishdate");
//        String imgurl = request.getParameter("imgurl");
//        String tid = request.getParameter("tid");
//        String bid =request.getParameter("bid");//图书id
//
//        Book book = new Book();
//        book.setBid(Integer.valueOf(bid));
//        book.setBname(bname);
//        book.setAuthor(author);
//
//        if (price!=null&&!"".equals(price)){
//            book.setPrice(Double.valueOf(price));
//        }
//        if (publishdate!=null&&!"".equals(publishdate)){
//            try {
//                book.setPublishdate(new SimpleDateFormat("yyyy-MM-dd").parse(publishdate));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        Type tp = new Type();
//        tp.setTid(Integer.valueOf(tid));
//        book.setType(tp);
//        //调用sevice
//        BookService bs =new BookServiceImpl();
//        boolean flag=bs.updateBook(book);
//        if (flag){
//            response.sendRedirect(request.getContextPath()+"/BooksServlet");
//        }
//    }
//
//    private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        String bid=request.getParameter("bid");
//        Book book = bookService.getBookByid(bid);
//        List<Type> tlist = bookService.addbook();
//        request.setAttribute("book",book);
//        request.setAttribute("tlist",tlist);
//        request.getRequestDispatcher("/updateBook.jsp").forward(request,response);
//    }
//    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        String bname = request.getParameter("bname");
//        String price = request.getParameter("price");
//        String author = request.getParameter("author");
//        String publishdate = request.getParameter("publishdate");
//        String imgurl = request.getParameter("imgurl");
//        String tid = request.getParameter("tid");
//
//        Book book = new Book();
//        book.setBname(bname);
//        book.setAuthor(author);
//
//        if (price!=null&&!"".equals(price)){
//            book.setPrice(Double.valueOf(price));
//        }
//        if (publishdate!=null&&!"".equals(publishdate)){
//            try {
//                book.setPublishdate(new SimpleDateFormat("yyyy-MM-dd").parse(publishdate));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        Type tp = new Type();
//        tp.setTid(Integer.valueOf(tid));
//        book.setType(tp);
//        //调用sevice
//        BookService bs =new BookServiceImpl();
//        boolean flag=bookService.addBook(book);
//        if (flag){
//            response.sendRedirect(request.getContextPath()+"/BooksServlet");
//        }
//    }
//
//    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Type> tlist= bookService.addbook();
//        request.setAttribute("tlist",tlist);
//        request.getRequestDispatcher("/addBook.jsp").forward(request,response);
//    }

    private void getBookList(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException {
        //获取查询条件
        String bname=request.getParameter("bname");

        String tid=request.getParameter("tid");

        Book book=new Book();//查询条件封装到book
        if (bname!=null&&!"".equals(bname)){
            book.setBname(bname);
        }
        if (tid!=null&&!"-1".equals(tid)){
            Type type=new Type();
            type.setTid(Integer.valueOf(tid));
            //把type设置给book对象
            book.setType(type);
        }

        String pages = request.getParameter("page");//当前页数

        int count= bookService.selectBooks(book); //获取总记录数

        //封装一个分页对象
        //PageInfo page=new PageInfo(pages,5,count);
        PageInfo<Book> page=new PageInfo<>(pages,5,count);

        page.setT(book);

        List<Book> b = bookService.selectBook(page);//总记录条件查询

        page.setList(b);
        //查询图书类型
        List<Type> tlist=bookService.addbook();
        request.setAttribute("page",page);//把当前页数的传给jsp
        request.setAttribute("tlist",tlist);

        request.getRequestDispatcher("/TuShu.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
