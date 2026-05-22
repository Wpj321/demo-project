package com.wpj.dao.Impl;

import com.wpj.dao.BookDao;
import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.util.JDBCUtil;
import com.wpj.util.PageInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @Override
    public List<Book> selectBook(PageInfo<Book> page) throws Exception {
        List<Book> list = new ArrayList<Book>();
        conn = JDBCUtil.getConnection();
        Book b = page.getT();
        if (b.getBname()!=null){//根据条件查询
            if (b.getType()!=null){
                String sql = "SELECT * from book b left join `t_type` t on b.tid=t.tid where b.bname like ? and b.tid=? limit ?,?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,"%"+b.getBname()+"%");
                pst.setInt(2,b.getType().getTid());
                pst.setInt(3,page.getStartIndex());
                pst.setInt(4,page.getPageSize());
            }else {
                String sql = "SELECT * from book b left join `t_type` t on b.tid=t.tid where b.bname like ? limit ?,?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,"%"+b.getBname()+"%");
                pst.setInt(2,page.getStartIndex());
                pst.setInt(3,page.getPageSize());
            }
        }else {
            if (b.getType()!=null){
                String sql = "SELECT * from book b left join `t_type` t on b.tid=t.tid where b.tid=? limit ?,?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,b.getType().getTid());
                pst.setInt(2,page.getStartIndex());
                pst.setInt(3,page.getPageSize());
            }else{
                String sql = "SELECT * from book b left join `t_type` t on b.tid=t.tid limit ?,?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,page.getStartIndex());
                pst.setInt(2,page.getPageSize());
            }
        }
        ResultSet rs =pst.executeQuery();
        while (rs.next()) {
            Book bk=new Book(rs.getInt("bid"), rs.getString("bname"), rs.getDouble("price"), rs.getString("author"), rs.getDate("publishdate"), rs.getString("imgurl"), new Type(rs.getInt("tid"),rs.getString("tname")));
            list.add(bk);
        }
        return list;
    }

    @Override
    public List<Type> addbooks() throws Exception {
        conn = JDBCUtil.getConnection();
        String sql = "SELECT * from t_type";
        pst = conn.prepareStatement(sql);
        ResultSet rs =pst.executeQuery();
        List<Type> list = new ArrayList<Type>();
        while (rs.next()) {
            Type tp= new Type(rs.getInt("tid"),rs.getString("tname"));
            list.add(tp);
        }
        return list;
    }

    @Override
    public void addBooks(Book book) throws SQLException {
        conn = JDBCUtil.getConnection();
        String sql = "insert into book(bname,price,publishdate,author,imgurl,tid) value(?,?,?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setString(1,book.getBname());
        pst.setDouble(2,book.getPrice());
        pst.setDate(3,new Date(book.getPublishdate().getTime()));
        pst.setString(4,book.getAuthor());
        pst.setString(5,book.getImgurl());
        pst.setInt(6,book.getType().getTid());
        pst.executeUpdate();
    }

    @Override
    public Book getBookById(String bid) throws SQLException {
        conn = JDBCUtil.getConnection();
        String sql = "select * from book where bid=?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1,Integer.valueOf(bid));
        ResultSet rs =pst.executeQuery();
        while (rs.next()) {
            return new Book(rs.getInt("bid"), rs.getString("bname"), rs.getDouble("price"), rs.getString("author"), rs.getDate("publishdate"), rs.getString("imgurl"), new Type(rs.getInt("tid"),null));
        }
        return null;
    }

    @Override
    public void updateBook(Book book) throws Exception{
        conn = JDBCUtil.getConnection();
        String sql = "update book set bname=?,price=?,publishdate=?,author=?,imgurl=?,tid=? where bid=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1,book.getBname());
        pst.setDouble(2,book.getPrice());
        pst.setDate(3,new Date(book.getPublishdate().getTime()));
        pst.setString(4,book.getAuthor());
        pst.setString(5,book.getImgurl());
        pst.setInt(6,book.getType().getTid());
        pst.setInt(7,book.getBid());
        pst.executeUpdate();
    }

    @Override
    public void deleteById(String bid) throws SQLException {
        conn = JDBCUtil.getConnection();
        String sql = "delete from book where bid=?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1,Integer.valueOf(bid));
        pst.executeUpdate();
    }

    @Override
    public int selectBooks(Book book) throws Exception {
        conn = JDBCUtil.getConnection();
        if (book.getBname()!=null){
            if (book.getType()!=null){
                String sql = "select count(bid)  from book where bname like ? and tid=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,"%"+book.getBname()+"%");
                pst.setInt(2,book.getType().getTid());
            }else {
                String sql = "select count(bid)  from book where bname like ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,"%"+book.getBname()+"%");
            }
        }else {
            if (book.getType()!=null){
                String sql = "select count(bid)  from book where tid=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1,book.getType().getTid());
            }else {
                String sql = "select count(bid)  from book";
                pst = conn.prepareStatement(sql);
            }
        }
        ResultSet rs =pst.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
