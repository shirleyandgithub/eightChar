package com.site.blog.my.core.controller.req;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserReq {
    private Long userId;

    private String username;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}