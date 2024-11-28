package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.controller.req.RecordReq;
import com.site.blog.my.core.controller.vo.RecordIdVO;
import com.site.blog.my.core.controller.vo.RecordVO;
import com.site.blog.my.core.dao.PersonEightMapper;
import com.site.blog.my.core.dao.RecordMapper;
import com.site.blog.my.core.entity.personeight.PersonEight;
import com.site.blog.my.core.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {



    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PersonEightMapper personEightMapper;

    @Override
    public List<RecordVO> recordList(Long userId) {
        return recordMapper.recordList(userId);
    }

    @Override
    public List<RecordIdVO> recordIdList(Long userId) {
        List<RecordVO> recordVOS = recordMapper.recordList(userId);
        if (!CollectionUtils.isEmpty(recordVOS)) {
            List<RecordIdVO> recordIdVOs = new ArrayList<>();
            recordVOS.forEach(recordVO -> {
                RecordIdVO recordIdVO = new RecordIdVO();
                recordIdVO.setId(recordVO.getId());
                recordIdVO.setName(recordVO.getName());
                recordIdVOs.add(recordIdVO);
            });
            return recordIdVOs;
        }
        return Collections.emptyList();
    }

    @Override
    public Long insert(RecordReq recordReq) {
        recordReq.setCreateTime(LocalDateTime.now());
        recordReq.setUpdateTime(LocalDateTime.now());
        recordMapper.insert(recordReq);
        return recordReq.getId();
    }

    @Override
    public int update(RecordReq recordReq) {
        recordReq.setUpdateTime(LocalDateTime.now());
        int recordUpdateRes = recordMapper.update(recordReq);
        if(recordUpdateRes>0){
            PersonEight personEight  = new PersonEight();
            personEight.setRecordId(recordReq.getId());
            personEight.setName(recordReq.getName());
            return personEightMapper.updateByRecordId(personEight);
        }
        return recordUpdateRes;
    }

    @Override
    public int delete(Long id) {
        return recordMapper.delete(id);
    }
}
