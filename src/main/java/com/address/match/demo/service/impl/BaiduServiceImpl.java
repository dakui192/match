package com.address.match.demo.service.impl;

import com.address.match.common.config.BaiduConfig;
import com.address.match.common.utils.HttpUtils;
import com.address.match.common.utils.Threads;
import com.address.match.demo.service.BaiduService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 21:12
 * @描述
 **/
@Service("baiduService")
public class BaiduServiceImpl implements BaiduService {

    private static final Logger logger = LoggerFactory.getLogger(BaiduServiceImpl.class);

    @Autowired
    BaiduConfig baiduConfig;
    /**
     * 1：访问百度地址解析
     * 2：对结果信息进行解析
     * @param address 需要解析的地址
     * @return Map
     */
    @Override
    public Map<String, Double> getGISByAddress(String address) {
        if (address!=null && !address.isEmpty()){
            String url=baiduConfig.getUrl()+"?address="+address+"&output="+baiduConfig.getOutput()+"&ak="+baiduConfig.getAk();
            logger.info(url);
            String resultJson=HttpUtils.sendURL(url);
            if (!StringUtils.isEmpty(resultJson)){
                return dealJson(resultJson);
            }
        }
        return null;
    }

    /**
     * 对返回的JSON 数据进行解析
     * @param resultJson
     * @return
     */
    private Map<String, Double> dealJson(String resultJson) {
        Map<String, Double> gisMap=new HashMap<>();
        JSONObject resultMap =  JSONObject.parseObject(resultJson);
        if ("0".equals(resultMap.getString("status"))){
            JSONObject resultJonj=JSONObject.parseObject(resultMap.getString("result"));
            if (resultJonj.containsKey("location")){
                JSONObject loc=JSONObject.parseObject(resultJonj.getString("location"));
                gisMap.put("lng",loc.getDoubleValue("lng"));
                gisMap.put("lat",loc.getDoubleValue("lat"));
            }
        }
        return gisMap;
    }
}
