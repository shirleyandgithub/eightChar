package com.site.blog.my.core.controller.vo.thirdapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Pillar {
    @JsonProperty("坤造")
    private String kunZao;
    @JsonProperty("纳音")
    private String nayin;
    @JsonProperty("空亡")
    private String voidWang;
    @JsonProperty("主星")
    private String mainStar;
    @JsonProperty("藏干")
    private List<List<String>> hiddenStems;
    @JsonProperty("星运")
    private String starFortune;
    @JsonProperty("自坐")
    private String selfSitting;
    @JsonProperty("神煞")
    private List<String> gods;
}
