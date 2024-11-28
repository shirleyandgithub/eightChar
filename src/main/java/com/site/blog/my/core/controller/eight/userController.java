package com.site.blog.my.core.controller.eight;

import com.site.blog.my.core.config.JwtTokenUtil;
import com.site.blog.my.core.controller.req.UserReq;
import com.site.blog.my.core.controller.vo.UserVO;
import com.site.blog.my.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class userController {

    @Resource
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("api/user/getUserById")
    public UserVO getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("api/user/login")
    public UserVO login(@RequestBody UserReq user) {
        UserVO userVO = userService.login(user.getUsername(), user.getPassword());
        if(userVO != null){
            String token = jwtTokenUtil.generateToken(userVO);
            userVO.setToken(token);
        }
        return userVO;
    }
}
