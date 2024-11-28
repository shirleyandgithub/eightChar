package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.controller.vo.UserVO;
import com.site.blog.my.core.dao.*;
import com.site.blog.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;


    @Override
    public UserVO getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserVO login(String username, String password) {
        return userMapper.login(username,password);
    }
}
