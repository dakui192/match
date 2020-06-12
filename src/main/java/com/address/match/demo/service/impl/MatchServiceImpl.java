package com.address.match.demo.service.impl;

import com.address.match.common.manager.AsyncManager;
import com.address.match.common.manager.factory.AsyncFactory;
import com.address.match.common.utils.IpUtils;
import com.address.match.common.utils.ServletUtils;
import com.address.match.common.web.AjaxResult;
import com.address.match.demo.data.dto.MatchOperaLog;
import com.address.match.demo.data.dto.MinRangeAddress;
import com.address.match.demo.data.dto.MostFrequeVisitInfo;
import com.address.match.demo.data.vo.MatchQueryDataVO;
import com.address.match.demo.mapper.MatchMapper;
import com.address.match.demo.service.BaiduService;
import com.address.match.demo.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 20:57
 * @描述
 **/
@Service("matchService")
public class MatchServiceImpl implements MatchService {

    @Autowired
    BaiduService baiduService;
    @Autowired
    MatchMapper matchMapper;

    /**
     * 1: 去百度地图查询用户输入地址的 经纬度
     * 2：查询数据库中是否存在符合地址的信息 返回给用户
     *  2.1:查询最近的中石化公司
     *  2.2:查询最近的福特公司
     * 3：组装最近的地址信息
     * 4：异步书写日志
     * @param matchQueryDataVO
     * @return
     */
    @Override
    public AjaxResult matchService(MatchQueryDataVO matchQueryDataVO) {
        Map<String,Double>  gisMap=baiduService.getGISByAddress(matchQueryDataVO.getAddress());
        if (!gisMap.isEmpty()){
            String fordName=matchMapper.getMinRangeFordByGisXAndGisY(gisMap.get("lng"),gisMap.get("lat"),5000);
            String sinpecName=matchMapper.getMinRangeSinopecByGisXAndGisY(gisMap.get("lng"),gisMap.get("lat"),5000);
            MinRangeAddress minRangeAddress=new MinRangeAddress();
            minRangeAddress.setFord_name(fordName);
            minRangeAddress.setSinopec_name(sinpecName);
            insertLog(minRangeAddress,matchQueryDataVO.getUser_id());
            return AjaxResult.success(minRangeAddress);
        }
        return AjaxResult.error("地址无法解析，请核实",null);
    }

    /**
     * 新增操作日志
     * @param minRangeAddress 用户查询出来的信息
     * @param user_id 用户id
     */
    private void insertLog(MinRangeAddress minRangeAddress, String user_id) {
        if (minRangeAddress!=null){
            List<MatchOperaLog> matchOperaLogList=new ArrayList<>();
            MatchOperaLog matchOperaLog=new MatchOperaLog();
            matchOperaLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
            matchOperaLog.setUser_id(StringUtils.isEmpty(user_id)?"sys":user_id);
            if (minRangeAddress.getSinopec_name()!=null){
                matchOperaLogList.add(dealOperaLog(matchOperaLog,"0",minRangeAddress.getSinopec_name()));
            }
            if (minRangeAddress.getFord_name()!=null){
                if (matchOperaLogList.size()>0){
                    matchOperaLogList.add(dealOperaLog(matchOperaLog.clone(),"1",minRangeAddress.getFord_name()));
                }else{
                    matchOperaLogList.add(dealOperaLog(matchOperaLog,"1",minRangeAddress.getFord_name()));
                }
            }
            if(!matchOperaLogList.isEmpty()){
                AsyncManager.me().execute(AsyncFactory.recordOper(matchOperaLogList));
            }
        }
    }

    /**
     * 操作的日志 进行处理补全
     * @param matchOperaLog 日志
     * @param status 状态
     * @param query_name 中石化或福特公司
     * @return
     */
    private MatchOperaLog dealOperaLog(MatchOperaLog matchOperaLog,String status,String query_name){
        matchOperaLog.setQuery_name(query_name);
        matchOperaLog.setState(status);
        return matchOperaLog;
    }

    /**
     * 判断用户id 是否正确
     * 查询数据库信息
     * @param user_id
     * @return
     */
    @Override
    public AjaxResult queryMostFreque(String user_id) {
        List<MostFrequeVisitInfo> mostFrequeVisitInfoList=matchMapper.queryMostFrequreVisitInfo(user_id);
        return AjaxResult.success(mostFrequeVisitInfoList);
    }

}
