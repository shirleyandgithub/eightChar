package com.site.blog.my.core.service;

import com.site.blog.my.core.controller.req.AddressReq;
import com.site.blog.my.core.controller.vo.AddressVO;
import java.util.List;


public interface AddressService {
    int create(AddressReq addressReq);

    List<AddressVO> list(Long userId);

    AddressVO selectByPrimaryKey(Long id, Long userId);

    int update(AddressReq addressReq);

    int delete(Long id, Long userId);

}
