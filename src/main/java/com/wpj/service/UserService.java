package com.wpj.service;

import com.wpj.pojo.User;

public interface UserService {
    User login(String uname, String password);
}
