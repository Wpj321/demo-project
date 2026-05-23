package com.wpj.service;

import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.util.PageInfo;

import java.util.List;

public interface BookService {
    public List<Book> selectBook (PageInfo<Book> page);

    public List<Type> addbook();

    public boolean addBook(Book book);

    public Book getBookByid(String bid);

    public boolean updateBook(Book book);

    public boolean deleteById(String bid);

    public int selectBooks (Book book);
    //测试1
}
