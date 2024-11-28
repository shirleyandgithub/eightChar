package com.site.blog.my.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.blog.my.core.controller.req.PersonEightReq;
import com.site.blog.my.core.controller.vo.thirdapi.BasicInfo;
import com.site.blog.my.core.controller.vo.thirdapi.BasicLifeChart;
import com.site.blog.my.core.controller.vo.thirdapi.Response;
import com.site.blog.my.core.controller.vo.thirdapi.RetData;
import com.site.blog.my.core.dao.BaseInfoMapper;
import com.site.blog.my.core.dao.PersonEightMapper;
import com.site.blog.my.core.entity.personeight.BaseInfo;
import com.site.blog.my.core.entity.personeight.FourData;
import com.site.blog.my.core.entity.personeight.FourDataList;
import com.site.blog.my.core.entity.personeight.PersonEight;
import com.site.blog.my.core.service.PersonEightService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonEightServiceImpl implements PersonEightService {

    @Autowired
    private PersonEightMapper personEightMapper;

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    @Resource
    private RestTemplate restTemplate;

    @Value("${third.api.sign}")
    private String sign;

    @Value("${third.api.appid}")
    private String appid;

    @Value("${third.api.url}")
    private String thirdUrl;

    @Override
    public PersonEight getByRecordId(Long recordId,Long userId) {
        BaseInfo baseInfo = baseInfoMapper.getByRecordId(recordId,userId);
        PersonEight personEight = personEightMapper.getByRecordId(recordId,userId);
        personEight.setBaseInfo(baseInfo);
        return personEight;
    }

    @Override
    public int create(PersonEightReq personEightReq) {
        // 请求第三方接口
        String url = thirdUrl +
                "?format=json" +
                "&appid="+appid +
                "&gx_birthday=" + personEightReq.getBirthday() +
                "&gx_gender=" + personEightReq.getGender() +
                "&gx_name=" + personEightReq.getName() +
                "&sign="+ sign;

        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Response response = responseEntity.getBody();
            if (response != null && response.getCodeid() == 10000) {
                RetData retdata = response.getRetdata();
                PersonEight personEight = new PersonEight();
                personEight.setName(retdata.getBasicInfo().getName());
                personEight.setRecordId(personEightReq.getRecordId());
                personEight.setUserId(personEightReq.getUserId());

                ObjectMapper objectMapperMajor = new ObjectMapper();
                try {
                    String retdataString = objectMapperMajor.writeValueAsString(retdata);
                    personEight.setJsonData(retdataString);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                personEight.setCreateTime(LocalDateTime.now());
                personEight.setUpdateTime(LocalDateTime.now());

                setMajor(personEight, retdata.getBasicLifeChart());
                setHeaven(personEight, retdata.getBasicInfo());
                setEarth(personEight, retdata.getBasicInfo());
                setHidden(personEight, retdata.getBasicLifeChart());
                setDeputyStar(personEight, retdata.getBasicLifeChart());
                setShenSha(personEight, retdata.getBasicLifeChart());
                setNaYin(personEight, retdata.getBasicLifeChart());
                setStarFortune(personEight, retdata.getBasicLifeChart());
                setKongWang(personEight, retdata.getBasicLifeChart());
                setBaseInfo(personEight, retdata.getBasicInfo());
                return personEightMapper.create(personEight);
            }
        }
        return -1;
    }

    @Override
    public int deleteByRecordId(Long recordId, Long userId) {
        // 先删除baseInfo
        int res = baseInfoMapper.deleteByRecordId(recordId,userId);
        if (res>0){
            return personEightMapper.deleteByRecordId(recordId,userId);
        } else {
            return res;
        }
    }

    @Override
    public List<PersonEight> list(Long userId) {
        return personEightMapper.list(userId);
    }

    private void setBaseInfo(PersonEight personEight, BasicInfo basicInfo) {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setRecordId(personEight.getRecordId());
        baseInfo.setGCalendar(basicInfo.getSolarCalendar()); // 阳历
        baseInfo.setLunarCalendar(basicInfo.getLunarCalendar()); // 农历
        baseInfo.setSolarTerms(basicInfo.getBirthSeason()); // 节气 todo  改字段名
        baseInfo.setConstellation(basicInfo.getConstellation());
        baseInfo.setKongWang(basicInfo.getVoidWang());
        baseInfo.setLifePalace(basicInfo.getLifePalace());
        baseInfo.setCreateTime(LocalDateTime.now());
        baseInfo.setUpdateTime(LocalDateTime.now());
        baseInfo.setUserId(personEight.getUserId());
        int create = baseInfoMapper.create(baseInfo);
        if(create>0){
            // todo 参数校验
            BaseInfo res = baseInfoMapper.getByRecordId(personEight.getRecordId(), personEight.getUserId());
            personEight.setBaseInfo(res);
            personEight.setBaseInfoId(baseInfo.getId());
        }
    }

    private void setMajor(PersonEight personEight, BasicLifeChart basicLifeChart) {
        FourData major = new FourData();
        major.setYear(basicLifeChart.getYearPillar().getMainStar());
        major.setMonth(basicLifeChart.getMonthPillar().getMainStar());
        major.setDay(basicLifeChart.getDayPillar().getMainStar());
        major.setHour(basicLifeChart.getHourPillar().getMainStar());
        ObjectMapper objectMapperMajor = new ObjectMapper();
        try {
            String majorString = objectMapperMajor.writeValueAsString(major);
            personEight.setMajor(majorString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setHeaven(PersonEight personEight, BasicInfo basicInfo) {
        FourData heaven = new FourData();
        heaven.setYear(basicInfo.getEightChars().get(0));
        heaven.setMonth(basicInfo.getEightChars().get(2));
        heaven.setDay(basicInfo.getEightChars().get(4));
        heaven.setHour(basicInfo.getEightChars().get(6));
        ObjectMapper objectMapperHeaven = new ObjectMapper();
        try {
            String heavenString = objectMapperHeaven.writeValueAsString(heaven);
            personEight.setHeaven(heavenString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setEarth(PersonEight personEight, BasicInfo basicInfo){
        FourData earth = new FourData();
        earth.setYear(basicInfo.getEightChars().get(1));
        earth.setMonth(basicInfo.getEightChars().get(3));
        earth.setDay(basicInfo.getEightChars().get(5));
        earth.setHour(basicInfo.getEightChars().get(7));
        ObjectMapper objectMapperEarth = new ObjectMapper();
        try {
            String earthString = objectMapperEarth.writeValueAsString(earth);
            personEight.setEarth(earthString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setHidden(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourDataList hidden = new FourDataList();
        hidden.setYear(extractHiddenStems(basicLifeChart.getYearPillar().getHiddenStems()));
        hidden.setMonth(extractHiddenStems(basicLifeChart.getMonthPillar().getHiddenStems()));
        hidden.setDay(extractHiddenStems(basicLifeChart.getDayPillar().getHiddenStems()));
        hidden.setHour(extractHiddenStems(basicLifeChart.getHourPillar().getHiddenStems()));

        ObjectMapper objectMapperHidden = new ObjectMapper();
        try {
            String hiddenString = objectMapperHidden.writeValueAsString(hidden);
            personEight.setHidden(hiddenString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> extractHiddenStems(List<List<String>> hiddenStems) {
        if (hiddenStems == null || hiddenStems.isEmpty()) {
            return Collections.emptyList();
        }
        return hiddenStems.stream()
                          .map(hiddenStem -> hiddenStem.get(0))
                          .collect(Collectors.toList());
    }

    private List<String> extractHiddenStemsDeputyStar(List<List<String>> hiddenStems) {
        if (hiddenStems == null || hiddenStems.isEmpty()) {
            return Collections.emptyList();
        }
        return hiddenStems.stream()
                      .map(hiddenStem -> hiddenStem.get(2))
                      .collect(Collectors.toList());
    }

    private void setDeputyStar(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourDataList deputyStar = new FourDataList();

        deputyStar.setYear(extractHiddenStemsDeputyStar(basicLifeChart.getYearPillar().getHiddenStems()));
        deputyStar.setMonth(extractHiddenStemsDeputyStar(basicLifeChart.getMonthPillar().getHiddenStems()));
        deputyStar.setDay(extractHiddenStemsDeputyStar(basicLifeChart.getDayPillar().getHiddenStems()));
        deputyStar.setHour(extractHiddenStemsDeputyStar(basicLifeChart.getHourPillar().getHiddenStems()));

        ObjectMapper objectMapperDeputyStar = new ObjectMapper();
        try {
            String deputyStarString = objectMapperDeputyStar.writeValueAsString(deputyStar);
            personEight.setDeputyStar(deputyStarString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setShenSha(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourDataList shenSha = new FourDataList();
        shenSha.setYear(basicLifeChart.getYearPillar().getGods());
        shenSha.setMonth(basicLifeChart.getMonthPillar().getGods());
        shenSha.setDay(basicLifeChart.getDayPillar().getGods());
        shenSha.setHour(basicLifeChart.getHourPillar().getGods());
        ObjectMapper objectMapperShenSha = new ObjectMapper();
        try {
            String shenShaString = objectMapperShenSha.writeValueAsString(shenSha);
            personEight.setShenSha(shenShaString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setNaYin(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourData naYin = new FourData();
        naYin.setYear(basicLifeChart.getYearPillar().getNayin());
        naYin.setMonth(basicLifeChart.getMonthPillar().getNayin());
        naYin.setDay(basicLifeChart.getDayPillar().getNayin());
        naYin.setHour(basicLifeChart.getHourPillar().getNayin());
        ObjectMapper objectMapperNaYin = new ObjectMapper();
        try {
            String naYinString = objectMapperNaYin.writeValueAsString(naYin);
            personEight.setNaYin(naYinString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setStarFortune(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourData starFortune = new FourData();
        starFortune.setYear(basicLifeChart.getYearPillar().getStarFortune());
        starFortune.setMonth(basicLifeChart.getMonthPillar().getStarFortune());
        starFortune.setDay(basicLifeChart.getDayPillar().getStarFortune());
        starFortune.setHour(basicLifeChart.getHourPillar().getStarFortune());
        ObjectMapper objectMapperStarFortune = new ObjectMapper();
        try {
            String starFortuneString = objectMapperStarFortune.writeValueAsString(starFortune);
            personEight.setStarFortune(starFortuneString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setKongWang(PersonEight personEight, BasicLifeChart basicLifeChart){
        FourData kongWang = new FourData();
        kongWang.setYear(basicLifeChart.getYearPillar().getVoidWang());
        kongWang.setMonth(basicLifeChart.getMonthPillar().getVoidWang());
        kongWang.setDay(basicLifeChart.getDayPillar().getVoidWang());
        kongWang.setHour(basicLifeChart.getHourPillar().getVoidWang());
        ObjectMapper objectMapperKongWang = new ObjectMapper();
        try {
            String kongWangString = objectMapperKongWang.writeValueAsString(kongWang);
            personEight.setKongWang(kongWangString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
