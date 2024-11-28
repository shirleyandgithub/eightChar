package com.site.blog.my.core.entity.personeight;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonEight {
    private Long id;

    private String name;

    private Long recordId;

    private String major;

    private String heaven;

    private String earth;

    private String hidden;

    private String deputyStar;

    private String shenSha;

    private String naYin;

    private String starFortune;

    private String kongWang;

    private Long baseInfoId;

    private BaseInfo baseInfo;

    private String jsonData;

    private Long userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}