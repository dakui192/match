package com.address.match.demo.service;

import com.address.match.demo.data.grab.FordInfo;
import com.address.match.demo.data.grab.SinopecInfo;

import java.util.List;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 3:26
 * @描述 抓取数据 业务层
 **/
public interface GrabDataService {
    /**
     * 去中石化官网抓取中石化数据
     * @param start 开始页
     * @param end 尾页
     * @return
     */
    public List<SinopecInfo> grabSinopecData(int start,int end);


    /**
     *  抓取福特 4S 店信息
     * @param start 开始页
     * @param end 尾页
     * @return
     */
    public List<FordInfo> grabFordData(int start,int end);
}
