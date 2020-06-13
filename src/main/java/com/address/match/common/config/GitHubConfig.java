package com.address.match.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 23:28
 * @描述
 **/
@Component
@ConfigurationProperties(prefix = "github")
@Data
public class GitHubConfig {
    /**
     * 客户编码
     */
    private String clientId;
    /**
     *密匙
     */
    private String clientSecret;
    /**
     * gitHub注册URL
     */
    private String authorizeUrl;
    /**
     * 回调URL
     */
    private String redirectUrl;
    /**
     * 获取Token URL
     */
    private String accessTokenUrl;
    /**
     * 获取用户信息的URL
     */
    private String userInfoUrl;
    /**
     * 登录URL
     */
    private String loginUrl;
}
