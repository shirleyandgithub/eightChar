package com.site.blog.my.core.dao;

import com.site.blog.my.core.controller.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    UserVO selectByPrimaryKey(Long id);

    UserVO login(@Param("username") String username, @Param("password") String password);
}