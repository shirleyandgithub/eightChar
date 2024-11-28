package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.personeight.BaseInfo;
import org.apache.ibatis.annotations.Param;

public interface BaseInfoMapper {

    int create(BaseInfo baseInfo);

    BaseInfo getByRecordId(@Param("recordId") Long recordId, @Param("userId") Long userId);

    int deleteByRecordId(@Param("recordId") Long recordId, @Param("userId") Long userId);

}