package com.wpj.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 功能描述：使用Druid连接池的工具类
 * 作者：zz
 * 时间：2021/12/1 8:59
 */
public class DruidUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            //加载资源文件
            InputStream inputStream = DruidUtil.class.getClassLoader().getResourceAsStream("database.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            //连接池的对象
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        try {
            if (conn == null || conn.isClosed()) {
                conn = dataSource.getConnection();
                //设置到ThreadLocal中
                threadLocal.set(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 方法描述：
     * 方法参数：
     * 返回值类型：
     * 作者：zz
     * 时间：2021/12/1 9:12
     */
    public static void close() {
        Connection conn = threadLocal.get();
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
                threadLocal.set(null);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
