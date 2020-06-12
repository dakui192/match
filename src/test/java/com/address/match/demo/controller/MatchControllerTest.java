package com.address.match.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 19:41
 * @描述
 **/
@SpringBootTest
class MatchControllerTest {
    @Autowired
    MatchController matchController;
    @Test
    void queryMinRange() {
        matchController.queryMinRange(null);
    }

    @Test
    void queryMostFreque() {
        matchController.queryMostFreque(null);
    }
}