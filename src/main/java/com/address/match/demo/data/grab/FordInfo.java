package com.address.match.demo.data.grab;

import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 11:55
 * @描述 福特公司4S店 信息抓取
 **/
@Data
public class FordInfo {
    /**
     * 经度
     */
    private Double lng=-180.0;
    /**
     * 维度
     */
    private Double lat=-90.0;
    /**
     * 地址信息
     */
    private String address;
    /**
     * 福特4S
     */
    private String ford_name;
    /**
     * 电话号码
     */
    private String phone;

    @Override
    public String toString() {
        return "insert into match_ford_info(lng,lat,address,ford_name,phone) values(" +
                lng +
                ", " + lat +
                ",'" + address + '\'' +
                ",'" + ford_name + '\'' +
                ",'" + phone + '\'' +
                ");";
    }
}
