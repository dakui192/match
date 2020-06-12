package com.address.match.demo.data.dto;

import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 0:25
 * @描述 最近距离的 中石化和福特4S 店
 **/
@Data
public class MinRangeAddress {
    /**
     * 中石化名称
     */
    private String sinopec_name;
    /**
     * 中石化名称
     */
    private String ford_name;
//    /**
//     * ip地址
//     */
//    private String ip;
//    /**
//     * 地址
//     */
//    private String x;
//    /**
//     * 电话
//     */
//    private String phone;
//    /**
//     * 性质 福特4S 或 中石化公司
//     */
//    private String type;
}
