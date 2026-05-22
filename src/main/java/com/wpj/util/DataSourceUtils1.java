package com.wpj.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils1 {

 /*
    Druid连接池使用
     1.导入jar包
     2.编写配置文件，放在src目录下
     3.通过Properties集合加载配置文件
     4.通过Druid连接池工厂类获取数据库连接池对象
     5.获取数据库连接，进行使用
 */

	/*

	  使用Druid数据源编写Jdbc工具类步骤如下

	 */

    //1.私有构造方法
    private DataSourceUtils1(){}

    //2.定义DataSource数据源变量
    private static DataSource dataSource;

    //3.提供静态代码块，完成配置文件的加载和获取连接池对象
    static {
        try{
            //加载配置文件
            InputStream is = DataSourceUtils1.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties prop = new Properties();
            prop.load(is);

            //获取数据库连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(prop);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //4.提供获取数据库连接的方法
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //5.提供获取数据库连接池的方法
    public static DataSource getDataSource() {
        return dataSource;
    }

    //6.提供释放资源的方法
    public static void close(Connection con, Statement stat, ResultSet rs) {
        if(con != null) {
            try {
                con.close();//把连接返回池子当中
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, Statement stat) {
        close(con,stat,null);
    }


}
