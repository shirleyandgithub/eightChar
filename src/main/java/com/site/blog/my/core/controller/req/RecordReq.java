package com.site.blog.my.core.controller.req;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordReq {

    private Long id;

    private String name;

    private int sex;

    private String birthday;

    private Long userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}