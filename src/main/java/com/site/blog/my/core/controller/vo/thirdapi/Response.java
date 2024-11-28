package com.site.blog.my.core.controller.vo.thirdapi;

import lombok.Data;

@Data
public class Response {
    private int codeid;
    private String message;
    private RetData retdata;
    private long curtime;
}
