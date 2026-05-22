package com.wpj.service.Impl;

import com.wpj.mapper.UserMapper;
import com.wpj.pojo.User;
import com.wpj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String uname, String password) {
        return userMapper.login(uname, password);
    }
}
