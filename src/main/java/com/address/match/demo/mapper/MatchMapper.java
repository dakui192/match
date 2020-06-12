package com.address.match.demo.mapper;

import com.address.match.demo.data.dto.MatchOperaLog;
import com.address.match.demo.data.dto.MostFrequeVisitInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 0:49
 * @描述
 **/
public interface MatchMapper {
    /**
     * 根据 经纬度 确定 最近的中石化公司
     *
     * @param lng   经度
     * @param lat   维度
     * @param scope 范围
     * @return 最近的 中石化公司
     */
    public String getMinRangeSinopecByGisXAndGisY(@Param("lng") double lng, @Param("lat") double lat, @Param("scope") double scope);

    /**
     * 根据 经纬度 确定 最近的中石化公司
     * @param lng 经度
     * @param lat 维度
     * @param scope 范围
     * @return 最近的 福特4S店
     */
    public String getMinRangeFordByGisXAndGisY(@Param("lng") double lng,@Param("lat") double lat,@Param("scope") double scope);

    /**
     * 查询 统中每个用户查询最频繁的加油站和4S店
     * @param user_id 用户id
     * @return
     */
    public List<MostFrequeVisitInfo> queryMostFrequreVisitInfo(@Param("user_id") String user_id);

    /**
     * 批量新增用户操作操作日志
     * @return 成功的条数
     */
    public int batchAddOperaLog(@Param("list") List<MatchOperaLog> list);
}
