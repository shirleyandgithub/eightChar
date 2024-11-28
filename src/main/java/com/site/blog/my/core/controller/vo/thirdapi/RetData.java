package com.site.blog.my.core.controller.vo.thirdapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RetData {
    @JsonProperty("基本信息")
    private BasicInfo basicInfo;
    @JsonProperty("基本命盘")
    private BasicLifeChart basicLifeChart;
}
