package com.site.blog.my.core.controller.req;



import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class PersonEightReq {

    /*档案id*/
    private Long recordId;

    /*命主姓名*/
    private String name;

    /*性别 女-0  男-1 */
    private Integer gender;

    /*出生日期  yyyy-mm-dd hh:mm*/
    private String birthday;

    private Long userId;

    //appid  sign 放配置文件
}