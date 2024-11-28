package com.site.blog.my.core.controller.vo.thirdapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BasicInfo {
    @JsonProperty("姓名")
    private String name;
    @JsonProperty("性别")
    private String gender;
    @JsonProperty("生肖")
    private String zodiac;
    @JsonProperty("八字")
    private List<String> eightChars;
    @JsonProperty("农历")
    private String lunarCalendar;
    @JsonProperty("阳历")
    private String solarCalendar;
    @JsonProperty("真太阳时")
    private String trueSunTime;
    @JsonProperty("人元司令")
    private String personInCharge;
    @JsonProperty("出生节气")
    private String birthSeason;
    @JsonProperty("上一节令")
    private String previousSeason;
    @JsonProperty("下一节令")
    private String nextSeason;
    @JsonProperty("胎元")
    private String fetalOrigin;
    @JsonProperty("命宫")
    private String lifePalace;
    @JsonProperty("身宫")
    private String bodyPalace;
    @JsonProperty("胎息")
    private String fetalBreath;
    @JsonProperty("星座")
    private String constellation;
    @JsonProperty("后天喜用")
    private Map<String, String> postnatalGods;
    @JsonProperty("五行力量")
    private FiveElementsStrength fiveElementsStrength;
    @JsonProperty("空亡")
    private String voidWang;
    private String lifeTrigram;
}
