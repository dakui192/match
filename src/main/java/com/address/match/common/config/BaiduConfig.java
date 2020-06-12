package com.address.match.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 21:03
 * @描述 百度地图配置API 信息
 **/
@Component
@ConfigurationProperties(prefix = "baidu")
@Data
public class BaiduConfig {
    /**
     * URL
     */
    private String url;
    /**
     * 密匙
     */
    private String ak;
    /**
     * 输出方式
     */
    private String output;
}
