package com.address.match.common.utils;

import com.address.match.demo.data.grab.SinopecInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 3:05
 * @描述 抓取中石化公司的工具类
 **/
public class GrabDataUtils {
    /**
     * 抓取中石化 公司数据
     * @return 已经抓取的数据
     */
    public static List<SinopecInfo> grabSinopec(){
        List<SinopecInfo> resultList=new ArrayList<>();
        for (int j=0;j<234;j++){
            String url = "https://www.sinopecsales.com/website/gasStationAction_queryGasStationByCondition.action?page.pageNo="+j;
            Document headMent= Jsoup.parse(loadSinpecURL(url,""));
            Elements e1=headMent.select("tbody tr");
            int len=Math.min(e1.size(),24);
            for (int i=1;i<=len;i++){
                Elements trs=e1.get(i).children();
                SinopecInfo sinopecInfo=new SinopecInfo();
                sinopecInfo.setSinopec_name(trs.get(1).text());
                sinopecInfo.setAddress(trs.get(2).text());
                sinopecInfo.setPhone(trs.get(4).text());
                resultList.add(sinopecInfo);
            }
        }
        return resultList;
    }

    public static String loadSinpecURL(String url,String charsetCode) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), charsetCode));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
