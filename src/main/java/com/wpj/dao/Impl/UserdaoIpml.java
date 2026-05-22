package com.wpj.dao.Impl;


import com.wpj.dao.Userdao;
import com.wpj.pojo.User;
import com.wpj.util.DruidUtil;
import com.wpj.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserdaoIpml implements Userdao {
    @Override
    public User findByUnamePwd(String uname, String pwd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where uname =? and password =?";
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ps.setString(2,pwd);
            rs = ps.executeQuery();

            while (rs.next()){
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close();
        }
        return  user;
    }
}
