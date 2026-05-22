package com.wpj.service.Impl;

import com.wpj.mapper.BookMapper;
import com.wpj.pojo.Book;
import com.wpj.pojo.Type;
import com.wpj.service.BookService;
import com.wpj.util.DataSourceUtils;
import com.wpj.util.JDBCUtil;
import com.wpj.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> selectBook(PageInfo<Book> page) {
            try {
                return bookMapper.getBookList(page);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放资源
                DataSourceUtils.close();
            }
            return null;
        }

    @Override
    public List<Type> addbook() {
        try {
            return bookMapper.addbooks();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return null;
    }

    @Override
    public boolean addBook(Book book) {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            bookMapper.addBooks(book);
            conn.commit();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            }catch (Exception a){
                e.printStackTrace();
            }
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return false;
    }

    @Override
    public Book getBookByid(String bid) {
        try {
            return bookMapper.getBookById(bid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            bookMapper.updateBook(book);
            conn.commit();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            }catch (Exception a){
                e.printStackTrace();
            }
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return false;
    }

    @Override
    public boolean deleteById(String bid) {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            bookMapper.deleteById(bid);
            conn.commit();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            }catch (Exception a){
                e.printStackTrace();
            }
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return false;
    }

    @Override
    public int selectBooks(Book book) {
        try {
            return bookMapper.selectBooks(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DataSourceUtils.close();
        }
        return 0;
    }
}
