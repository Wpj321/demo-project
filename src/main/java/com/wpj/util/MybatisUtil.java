package com.wpj.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private  static SqlSessionFactory sqlSessionFactory;
    /**
     * 功能描述：获取SqlSession对象，关闭Sqlsession对象
     * SqlSessionFactory获取，只需要实例化一次
     */
    static {
        try {
            //使用三个核心对象调用maybites相关程序
            Reader reader = Resources.getResourceAsReader("mybatis_confing.xml");
            //创建sqlSessionFactoryBuilder实现sqlSessionFactory
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //船舰SqlSessionFactory实现SqlSession对象
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlSession对象
     */
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    /**
     * 关闭SqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession){
        sqlSession.close();
        return;
    }
}
