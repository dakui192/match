package com.address.match.demo.service.impl;

import com.address.match.demo.service.BaiduService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 18:52
 * @描述
 **/
@SpringBootTest
class BaiduServiceImplTest {
    @Autowired
    BaiduService baiduService;

    @Test
    void getGISByAddress() {
        Map<String,Double> gisMap=baiduService.getGISByAddress("重庆市万州区沙龙路三段凯源商");
        if (!gisMap.isEmpty()){
            System.out.println(gisMap.get("lng"));
            System.out.println(gisMap.get("lat"));
        }
    }
}