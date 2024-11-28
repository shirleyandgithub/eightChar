package com.site.blog.my.core.entity.personeight;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Address {
    private Long id;

    private String userId;

    private String username;

    private String phone;

    private String address;

    private String detailAddress;

    private Integer setDefault;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}