package com.site.blog.my.core.controller.vo.thirdapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FiveElementsStrength {
    @JsonProperty("金")
    private String metal;
    @JsonProperty("水")
    private String water;
    @JsonProperty("木")
    private String wood;
    @JsonProperty("火")
    private String fire;
     @JsonProperty("土")
    private String earth;
}
