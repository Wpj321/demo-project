package com.wpj.dao;

import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.util.PageInfo;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<Book> selectBook(PageInfo<Book> page) throws Exception;

    public List<Type> addbooks() throws Exception;

    public void addBooks(Book book) throws SQLException;

    public Book getBookById(String bid) throws SQLException;

    public void updateBook(Book book) throws Exception;

    public void deleteById(String bid) throws SQLException;

    public int selectBooks(Book book) throws Exception;
}
