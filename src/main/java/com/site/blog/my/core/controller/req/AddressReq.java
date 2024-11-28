package com.site.blog.my.core.controller.req;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AddressReq {

    private Long id;

    private Long userId;

    private String username;

    private String phone;

    private String address;

    private String detailAddress;

    private Integer setDefault;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}