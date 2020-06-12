package com.address.match.demo.mapper;

import com.address.match.demo.data.dto.MostFrequeVisitInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 2:54
 * @描述 Mapper 单元测试
 **/
@SpringBootTest
class MatchMapperTest {

    @Autowired
    MatchMapper matchMapper;

    /**
     * 测试5公里内 最近获取中石化公司
     */
    @Test
    void getMinRangeSinopecByGisXAndGisY(){
        System.out.println(matchMapper.getMinRangeSinopecByGisXAndGisY(100,25,5000));
    }

    /**
     * 测试5公里内 最近获取中福特4S店
     */
    @Test
    void getMinRangeFordByGisXAndGisY(){
        System.out.println(matchMapper.getMinRangeFordByGisXAndGisY(100,25,5000));
    }

    /**
     * 测试查询做频繁的 中石化公司和福特4S店
     */
    @Test
    void queryMostFrequreVisitInfo(){
        List<MostFrequeVisitInfo> mostFrequeVisitInfoList= matchMapper.queryMostFrequreVisitInfo(null);
        if (mostFrequeVisitInfoList!=null && !mostFrequeVisitInfoList.isEmpty()){
            mostFrequeVisitInfoList.stream().forEach(m->{
                System.out.println(m.toString());
            });
        }
    }
}