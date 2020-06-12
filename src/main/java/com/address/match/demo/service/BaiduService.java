package com.address.match.demo.service;

import java.util.Map;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 21:12
 * @描述 baidu 地址解析
 **/
public interface BaiduService {
    /**
     * 通过地址 解析出GIS 信息
     * @param address 需要解析的地址
     * @return Map 经纬度
     */
    public Map<String,Double> getGISByAddress(String address);
}
