package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.controller.req.AddressReq;
import com.site.blog.my.core.controller.vo.AddressVO;
import com.site.blog.my.core.dao.AddressMapper;
import com.site.blog.my.core.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {



    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int create(AddressReq addressReq) {
        defaultAddressCheck(addressReq);
        addressReq.setCreateTime(LocalDateTime.now());
        addressReq.setUpdateTime(LocalDateTime.now());
        return addressMapper.create(addressReq);
    }

    @Override
    public List<AddressVO> list(Long userId) {
        return addressMapper.list(userId);
    }

    @Override
    public AddressVO selectByPrimaryKey(Long id, Long userId) {
        return addressMapper.selectByPrimaryKey(id, userId);
    }

    @Override
    public int update(AddressReq addressReq) {
        // 默认地址修改
        defaultAddressCheck(addressReq);
        addressReq.setUpdateTime(LocalDateTime.now());
        return addressMapper.update(addressReq);
    }

    private void defaultAddressCheck(AddressReq addressReq){
        if(addressReq.getSetDefault()==1){
            List<AddressVO> list = addressMapper.list(addressReq.getUserId());
            for (AddressVO addressVO : list){
                if(addressVO.getSetDefault()==1){
                    addressVO.setSetDefault(0);
                    AddressReq vo2Req = getAddressReq(addressVO);
                    addressMapper.update(vo2Req);
                }
            }
        }
    }

    private static AddressReq getAddressReq(AddressVO addressVO) {
        AddressReq addressReq = new AddressReq();
        addressReq.setId(addressVO.getId());
        addressReq.setSetDefault(addressVO.getSetDefault());
        addressReq.setUserId(addressVO.getUserId());
        addressReq.setUsername(addressVO.getUsername());
        addressReq.setPhone(addressVO.getPhone());
        addressReq.setAddress(addressVO.getAddress());
        addressReq.setDetailAddress(addressVO.getDetailAddress());
        addressReq.setCreateTime(addressVO.getCreateTime());
        addressReq.setUpdateTime(addressVO.getUpdateTime());
        return addressReq;
    }

    @Override
    public int delete(Long id, Long userId) {
        return addressMapper.delete(id, userId);
    }
}
