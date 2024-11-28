package com.site.blog.my.core.entity;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;

    private String username;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}