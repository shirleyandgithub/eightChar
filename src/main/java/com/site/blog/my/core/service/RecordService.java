package com.site.blog.my.core.service;

import com.site.blog.my.core.controller.req.RecordReq;
import com.site.blog.my.core.controller.vo.RecordIdVO;
import com.site.blog.my.core.controller.vo.RecordVO;

import java.util.List;


public interface RecordService {
    /**
     * 获取档案列表
     *
     * @param
     * @return
     */
    List<RecordVO> recordList(Long userId);

    List<RecordIdVO> recordIdList(Long userId);


    /**
     * 新增档案
     *
     * @param
     * @return
     */
    Long insert(RecordReq recordReq);


    /**
     * 编辑档案
     *
     * @param
     * @return
     */
    int update(RecordReq recordReq);


    /**
     * 删除档案
     *
     * @param
     * @return
     */
    int delete(Long id );
}
