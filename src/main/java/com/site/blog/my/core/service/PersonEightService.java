package com.site.blog.my.core.service;

import com.site.blog.my.core.controller.req.PersonEightReq;
import com.site.blog.my.core.controller.req.RecordReq;
import com.site.blog.my.core.controller.vo.RecordVO;
import com.site.blog.my.core.entity.personeight.PersonEight;

import java.util.List;


public interface PersonEightService {

    /**
     * 根据recordId获取八字数据
     *
     * @param
     * @return
     */
    PersonEight getByRecordId(Long recordId,Long userId);

    /**
     * 新增八字数据
     *
     * @param
     * @return
     */
    int create(PersonEightReq personEightReq);

    /**
     * 删除八字数据
     *
     * @param
     * @return
     */
    int deleteByRecordId(Long recordId, Long userId);

    /**
     * 获取八字列表
     *
     * @param
     * @return
     */
    List<PersonEight> list(Long userId);
}
