package com.site.blog.my.core.service;

import com.site.blog.my.core.controller.vo.UserVO;
import com.site.blog.my.core.entity.User;


public interface UserService {
    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    UserVO getUserById(Long id);

    /**
     * 登录
     *
     * @param username password
     * @return
     */
    UserVO login(String username, String password);

}
