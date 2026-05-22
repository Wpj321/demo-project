package com.wpj.mapper;

import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.util.PageInfo;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

public interface BookMapper {
    public List<Book> getBookList(PageInfo<Book> page) throws Exception;

    @Select("SELECT * from type")
    public List<Type> addbooks() throws Exception;

    public void addBooks(Book book) throws SQLException;

    public Book getBookById(String bid) throws SQLException;

    public void updateBook(Book book) throws Exception;

    public void deleteById(String bid) throws SQLException;

    public int selectBooks(Book book) throws Exception;
}
