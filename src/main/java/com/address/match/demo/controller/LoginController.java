package com.address.match.demo.controller;

import com.address.match.common.config.GitHubConfig;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 23:35
 * @描述 第三方登录验证
 **/
@RestController
@Api(value = "GitHub第三方登录验证接口")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    GitHubConfig gitHubConfig;

    @GetMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) {
        String url = gitHubConfig.getAuthorizeUrl() +
                "?client_id=" + gitHubConfig.getClientId() +
                "&redirect_uri=" + gitHubConfig.getRedirectUrl();
        log.info("授权url:{}", url);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("单点登录重定向出错 error{}",e);
        }
    }

    /**
     * 回调函数 ，用户同意授权后，GitHub会重定向到此路径
     * @param code GitHub重定向时附加的授权码
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        log.info("code={}", code);
        try {
            // code换token
            String accessToken = getAccessToken(code);
            return "用户信息:" + getUserInfo(accessToken);
        }catch (Exception e){
            return "连接超时：请重试！";
        }
    }

    /**
     * 获取Token
     * @param code
     * @return
     */
    private String getAccessToken(String code) throws Exception{
        String url = gitHubConfig.getAccessTokenUrl() +
                "?client_id=" + gitHubConfig.getClientId() +
                "&client_secret=" + gitHubConfig.getClientSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        log.info("getAccessToken url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseStr = response.getBody();
        log.info("responseStr={}", responseStr);
        // 解析响应json字符串
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        String accessToken = jsonObject.getString("access_token");
        log.info("accessToken={}", accessToken);
        return accessToken;
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     */
    private String getUserInfo(String accessToken) throws Exception{
        String url = gitHubConfig.getUserInfoUrl();
        log.info("getUserInfo url:{}", url);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // AccessToken放在请求头中
        requestHeaders.add("Authorization", "token " + accessToken);
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String userInfo = response.getBody();
        log.info("userInfo={}", userInfo);
        return userInfo;
    }
}
