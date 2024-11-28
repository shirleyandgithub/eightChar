package com.site.blog.my.core.controller.vo.thirdapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BasicLifeChart {
    @JsonProperty("年柱")
    private Pillar yearPillar;
    @JsonProperty("月柱")
    private Pillar monthPillar;
    @JsonProperty("日柱")
    private Pillar dayPillar;
    @JsonProperty("时柱")
    private Pillar hourPillar;
}
