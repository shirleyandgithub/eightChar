package com.site.blog.my.core.controller.eight;

import com.site.blog.my.core.controller.req.RecordReq;
import com.site.blog.my.core.controller.vo.RecordIdVO;
import com.site.blog.my.core.controller.vo.RecordVO;
import com.site.blog.my.core.controller.vo.Response;
import com.site.blog.my.core.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class recordController {

    @Resource
    private RecordService recordService;

    @GetMapping("api/record/list")
    public Response<List<RecordVO>> recordList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return Response.failure(500, "未获取到userId");
        }
        return Response.success(recordService.recordList(userId));
    }

    @GetMapping("api/record/recordIdList")
    public Response<List<RecordIdVO>> recordIdList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return Response.failure(500, "未获取到userId");
        }
        return Response.success(recordService.recordIdList(userId));
    }

    @PostMapping("api/record/add")
    public Response<Long> insert(@RequestBody RecordReq recordReq, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return Response.failure(500, "未获取到userId");
        }
        recordReq.setUserId(userId);
        return Response.success(recordService.insert(recordReq));
    }

    @PostMapping("api/record/update")
    public Response<Integer> update(@RequestBody RecordReq recordReq, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return Response.failure(500, "未获取到userId");
        }
        recordReq.setUserId(userId);
        int result = recordService.update(recordReq);
        if(result>0){
            return Response.success(result);
        } else {
            return Response.failure(500, "更新失败");
        }
    }

    @DeleteMapping("api/record/delete")
    public Response<Integer> delete(@RequestParam Long id) {
        int result = recordService.delete(id);
        if(result>0){
            return Response.success(result);
        } else {
            return Response.failure(500, "删除失败");
        }
    }

}
