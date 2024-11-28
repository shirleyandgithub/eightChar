package com.site.blog.my.core.controller.vo;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "操作成功", data);
    }

    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(200, msg, data);
    }

    public static <T> Response<T> failure(int code, String msg) {
        return new Response<>(code, msg, null);
    }
}
