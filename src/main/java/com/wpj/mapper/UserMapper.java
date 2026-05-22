package com.wpj.mapper;

import com.wpj.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where uname=#{name} and password=#{pwd}")
    User login(@Param("name") String uname, @Param("pwd") String password);
    //User login(String uname, String password);
}
