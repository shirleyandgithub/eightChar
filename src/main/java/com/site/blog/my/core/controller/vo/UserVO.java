package com.site.blog.my.core.controller.vo;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private Long userId;

    private String username;

    private String password;

    private String token;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}