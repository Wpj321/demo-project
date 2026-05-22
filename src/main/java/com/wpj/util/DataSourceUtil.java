package com.wpj.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceUtil {
	
	//1.私有构造方法
	private DataSourceUtil(){}
	//2.定义DataSource数据源变量
	private static DataSource dataSource=null;
	//3.使用ThreadLocal存储当前线程中的Connection对象
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    //本地线程池，内部维护了一个map集合，它以当前线程为key，以conn为value


    static{
    	
    	 try{
             //加载配置文件
             InputStream is = DataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties");
             Properties prop = new Properties();
             prop.load(is);

             //获取数据库连接池对象
             dataSource = DruidDataSourceFactory.createDataSource(prop);

         } catch(Exception e) {
             e.printStackTrace();
         }
    	
    }
    //获取连接的方法
    public static Connection getConnection()  throws Exception{

         //从当前线程中获取Connection
        Connection conn = threadLocal.get();
        if(conn==null){
            //从数据源中获取数据库连接
            conn = getDataSource().getConnection();
            //将conn绑定到当前线程
            threadLocal.set(conn);
        }
        return conn;


    }
    
    //关闭连接的方法
    public static void close(){
        try{
            //从当前线程中获取Connection
            Connection conn = threadLocal.get();
            if(conn!=null){             
                threadLocal.remove(); //解除当前线程上绑定的连接
                conn.close();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    //从数据源中获取数据库连接池
    public static DataSource getDataSource(){
        return dataSource;
    }   
    
    //开启事务的操作
    public static void startTransaction(){

        Connection connection=threadLocal.get();
        if(null == connection){
            try {
                connection=getDataSource().getConnection();//若不存在连接，从数据源连接池创一个新连接
            } catch (SQLException e) {
                e.printStackTrace();
            }           
            threadLocal.set(connection); //把数据库连接绑定到当前线程上，来确保不同操作使用同一个连接

        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }               
    }

    //事务提交
    public static void commit(){

        Connection connection = threadLocal.get();
        if(null != connection){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //事务回滚
    public static void rollback(){
        Connection connection = threadLocal.get();
        if(null != connection){

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
