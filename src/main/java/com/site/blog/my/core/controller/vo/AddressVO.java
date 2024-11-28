package com.site.blog.my.core.controller.vo;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressVO {

    private Long id;

    private Long userId; // 用户id

    private String username;

    private String phone;

    private String address;

    private String detailAddress;

    private Integer setDefault;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}