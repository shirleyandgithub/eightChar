package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.personeight.PersonEight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonEightMapper {

    int create(PersonEight personEight);

    int updateByRecordId(PersonEight personEight);

    PersonEight getByRecordId(@Param("recordId") Long recordId, @Param("userId") Long userId);

    int deleteByRecordId(@Param("recordId") Long recordId, @Param("userId") Long userId);

    List<PersonEight> list(Long userId);

}