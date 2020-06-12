package com.address.match.demo.data.dto;

import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 0:38
 * @描述 用户访问最频繁的信息
 **/
@Data
public class MostFrequeVisitInfo {
    /***
     * 用户ID
     */
    private String  user_id;
    /**
     * 中石化公司名称
     */
    private String sinopec_name;
    /**
     * 福特4S名称
     */
    private String ford_name;
}
