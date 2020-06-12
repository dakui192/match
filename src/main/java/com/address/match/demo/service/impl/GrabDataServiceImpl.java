package com.address.match.demo.service.impl;

import com.address.match.common.utils.GrabDataUtils;
import com.address.match.demo.data.grab.FordInfo;
import com.address.match.demo.data.grab.SinopecInfo;
import com.address.match.demo.service.BaiduService;
import com.address.match.demo.service.GrabDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 3:29
 * @描述
 **/
@Service("grabDataService")
public class GrabDataServiceImpl implements GrabDataService {
    @Autowired
    BaiduService baiduService;

    /**
     *@描述
     *  抓取中石化官网参数
     */
    @Override
    public List<SinopecInfo> grabSinopecData(int start,int end) {
        List<SinopecInfo> sinopecInfoList= grabSinopec(start,end);
        if (!sinopecInfoList.isEmpty()){
            sinopecInfoList.stream().forEach(s->{
                Map<String,Double> gisMap=baiduService.getGISByAddress(s.getSinopec_name());
                if (gisMap!=null && !gisMap.isEmpty()){
                    s.setLng(gisMap.get("lng"));
                    s.setLat(gisMap.get("lat"));
                }

            });
        }
        return sinopecInfoList;
    }

    /**
     * 抓取福特4S店信息
     * @return
     */
    @Override
    public List<FordInfo> grabFordData(int start,int end) {
        List<FordInfo> fordInfoList= grabFord(start,end);
        if (!fordInfoList.isEmpty()){
            fordInfoList.stream().forEach(s->{
                Map<String,Double> gisMap=baiduService.getGISByAddress(s.getFord_name());
                if (gisMap!=null && !gisMap.isEmpty()){
                    s.setLng(gisMap.get("lng"));
                    s.setLat(gisMap.get("lat"));
                }

            });
        }
        return fordInfoList;
    }

    /**
     * 抓取福特4s 店信息
     * 1：循环访问 福特官网
     * 2：解析出需要的参数信息
     * 3：封装返回
     * @return 已经抓取的数据
     */
    private List<FordInfo> grabFord(int start,int end) {
        List<FordInfo> resultList=new ArrayList<>();
        for (int j=start;j<=end;j++){
//            for (int j=0;j<=40;j++){
            String url = "http://www.autobaidu.com/4slist.php?&brand=57534&page="+j;
            Document headMent= Jsoup.parse(GrabDataUtils.loadSinpecURL(url,"UTF-8"));
            Elements ddEl=headMent.select("form div dl dd");
            ddEl.stream().forEach(dd->{
                Elements fordName=dd.select("h5 a");
                if (fordName.size()>0){
                    FordInfo fordInfo=new FordInfo();
                    fordInfo.setFord_name(fordName.get(0).text());
                    Elements otherEl=dd.select("p");
                    if (otherEl.size()>2){
                        fordInfo.setAddress(otherEl.get(1).text().split("：")[1].replace("查看地图","").trim());
                        fordInfo.setPhone(otherEl.get(2).text().split("：")[1].replace(",","/").replace("or","/"));
                    }
                    resultList.add(fordInfo);
                }
            });
        }
        return resultList;
    }

    /**
     * 抓取中石化 公司数据
     * 1：循环访问 中石化官网
     * 2：解析出需要的参数信息
     * 3：封装返回
     * @return 已经抓取的数据
     */
    private List<SinopecInfo> grabSinopec(int start,int end) {
        List<SinopecInfo> resultList=new ArrayList<>();
        for (int j=start;j<end;j++){
//            for (int j=0;j<234;j++){
            String url = "https://www.sinopecsales.com/website/gasStationAction_queryGasStationByCondition.action?page.pageNo="+j;
            Document headMent= Jsoup.parse(GrabDataUtils.loadSinpecURL(url,"GB18030"));
            Elements e1=headMent.select("tbody tr");
            int len=Math.min(e1.size(),24);
            for (int i=1;i<=len;i++){
                Elements trs=e1.get(i).children();
                SinopecInfo sinopecInfo=new SinopecInfo();
                if (trs!=null && trs.size()>4) {
                    sinopecInfo.setSinopec_name(trs.get(1).text());
                    sinopecInfo.setAddress(trs.get(2).text());
                    sinopecInfo.setPhone(trs.get(4).text().replace(",","/").replace("or","/"));
                    resultList.add(sinopecInfo);
                }
            }
        }
        return resultList;
    }
}
