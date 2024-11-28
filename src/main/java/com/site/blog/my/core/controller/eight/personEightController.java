package com.site.blog.my.core.controller.eight;

import com.site.blog.my.core.controller.req.PersonEightReq;
import com.site.blog.my.core.controller.vo.Response;
import com.site.blog.my.core.entity.personeight.PersonEight;
import com.site.blog.my.core.service.PersonEightService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class personEightController {

    @Resource
    private PersonEightService personEightService;


    // 根据recordId获取八字数据
    @GetMapping("api/person-eight/getByRecordId")
    public PersonEight getByRecordId(@RequestParam Long recordId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return null;
        }
        return personEightService.getByRecordId(recordId, userId);
    }

    // 新增八字数据接口
    @PostMapping("api/person-eight/create")
    public Response<Integer> create(@RequestBody PersonEightReq personEightReq, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        personEightReq.setUserId(userId);
        int result = personEightService.create(personEightReq);
        if(result>0){
            return Response.success(result);
        } else {
            return Response.failure(500, "新增失败");
        }
    }

    // 删除八字数据接口
    @DeleteMapping("api/person-eight/deleteByRecordId")
    public Response<Integer> deleteByRecordId(@RequestParam Long recordId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return Response.failure(500, "未获取到userId");
        }
        int result = personEightService.deleteByRecordId(recordId, userId);
        if(result>0){
            return Response.success(result);
        } else {
            return Response.failure(500, "删除失败");
        }
    }

    // 获取八字列表
    @GetMapping("api/person-eight/list")
    public List<PersonEight> list(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId==null){
            return null;
        }
        return personEightService.list(userId);
    }
}
