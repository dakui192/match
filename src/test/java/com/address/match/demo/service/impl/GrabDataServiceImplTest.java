package com.address.match.demo.service.impl;

import com.address.match.demo.data.grab.FordInfo;
import com.address.match.demo.data.grab.SinopecInfo;
import com.address.match.demo.service.GrabDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 12:17
 * @描述
 **/
@SpringBootTest
class GrabDataServiceImplTest {
    @Autowired
    GrabDataService grabDataService;
    @Test
    void grabSinopecData() {
        //总共234页
        List<SinopecInfo> sinopecInfoList=grabDataService.grabSinopecData(1,2);
        if (!sinopecInfoList.isEmpty()){
            sinopecInfoList.stream().forEach(s->{
                System.out.println(s.toString());
            });
        }
    }


    @Test
    void grabFordData() {
        //总共40页
        List<FordInfo> fordInfoList=grabDataService.grabFordData(2,3);
        if (!fordInfoList.isEmpty()){
            fordInfoList.stream().forEach(s->{
                System.out.println(s.toString());
            });
        }
    }
}