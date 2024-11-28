package com.site.blog.my.core.controller.eight;

import com.site.blog.my.core.controller.req.AddressReq;
import com.site.blog.my.core.controller.vo.AddressVO;
import com.site.blog.my.core.controller.vo.Response;
import com.site.blog.my.core.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class addressController {

    @Resource
    private AddressService addressService;

    @PostMapping("api/address/create")
    public Response<Integer> create(@RequestBody AddressReq addressReq, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        addressReq.setUserId(userId);
        return Response.success(addressService.create(addressReq));
    }

    @GetMapping("api/address/list")
    public Response<List<AddressVO>> list(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        return Response.success(addressService.list(userId));
    }

    @GetMapping("api/address/getById")
    public Response<AddressVO> selectByPrimaryKey(@RequestParam Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        return Response.success(addressService.selectByPrimaryKey(id, userId));
    }

    @PostMapping("api/address/update")
    public Response<Integer> update(@RequestBody AddressReq addressReq, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        addressReq.setUserId(userId);
        return Response.success(addressService.update(addressReq));
    }

    @DeleteMapping("api/address/delete")
    public Response<Integer> delete(@RequestParam Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        return Response.success(addressService.delete(id, userId));
    }
}
