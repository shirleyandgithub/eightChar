package com.site.blog.my.core.dao;

import com.site.blog.my.core.controller.req.RecordReq;
import com.site.blog.my.core.controller.vo.RecordVO;

import java.util.List;

public interface RecordMapper {

    List<RecordVO> recordList(Long userId);

    int insert(RecordReq recordReq);

    int update(RecordReq recordReq);

    int delete(Long id);
}