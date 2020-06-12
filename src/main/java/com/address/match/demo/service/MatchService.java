package com.address.match.demo.service;

import com.address.match.common.web.AjaxResult;
import com.address.match.demo.data.dto.MatchOperaLog;
import com.address.match.demo.data.vo.MatchQueryDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 20:56
 * @描述
 **/
public interface MatchService {
    /**
     * 查询 5公里内最近的 中石化和福特4S店信息
     * @param matchQueryDataVO
     *      address : 地址信息
     *      user_id : 用户id
     * @return
     */
    AjaxResult matchService(MatchQueryDataVO matchQueryDataVO);

    /**
     * 查询系统中每个用户查询最频繁的加油站和4S店
     * @param user_id
     * @return
     */
    AjaxResult queryMostFreque(String user_id);

}
