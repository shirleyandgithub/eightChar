package com.site.blog.my.core.entity.personeight;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseInfo {
    private Long id;

    private Long recordId;

    private String gCalendar;

    private String lunarCalendar;

    private String solarTerms;

    private String constellation;

    private String kongWang;

    private String lifePalace;

    private Long userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}