package com.wpj.dao;

import com.wpj.pojo.User;

public interface Userdao {

    User findByUnamePwd(String uname, String pwd);
}
