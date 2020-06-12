package com.address.match.demo.service.impl;

import com.address.match.demo.data.vo.MatchQueryDataVO;
import com.address.match.demo.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 18:57
 * @描述
 **/
@SpringBootTest
class MatchServiceImplTest {
    @Autowired
    MatchService matchService;

    @Test
    void matchService() {
        MatchQueryDataVO matchQueryDataVO=new MatchQueryDataVO();
        matchQueryDataVO.setAddress("北京市海淀区上地十街10号");
        matchQueryDataVO.setUser_id("11111");
        matchService.matchService(matchQueryDataVO);
    }

    @Test
    void queryMostFreque() {
        matchService.queryMostFreque(null);
    }
}