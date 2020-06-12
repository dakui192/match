package com.address.match.demo.controller;

import com.address.match.common.web.AjaxResult;
import com.address.match.demo.data.vo.MatchQueryDataVO;
import com.address.match.demo.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 fbk
 * @创建时间 2020/6/6 20:56
 * @描述 控制器层
 **/
@RestController
@RequestMapping("/query")
@Api(value = "位置匹配信息接口")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/min_range.do")
    @ApiOperation(value="位置定位信息",notes = "用户输入任意道路交叉口或者重要地标查询半径5公里范围内最近的中石化加油站和福特4S店")
    //@ApiImplicitParam(name = "matchQueryDataVO", value = "信息查询", required = true,dataType = "MatchQueryDataVO")
    public AjaxResult queryMinRange(MatchQueryDataVO matchQueryDataVO){
        if (matchQueryDataVO!=null && !StringUtils.isEmpty(matchQueryDataVO.getAddress())) {
            return matchService.matchService(matchQueryDataVO);
        }
        return AjaxResult.error("请输入正确的地址信息",null);
    }

    @GetMapping("/most_freque.do")
    @ApiOperation(value="统计接口",notes = "统计用户查询最频繁的加油站和4S店")
    @ApiImplicitParam(name = "user_id", value = "用户id", required = false, dataType = "String")
    public AjaxResult queryMostFreque(String user_id){
        return matchService.queryMostFreque(user_id);
    }

}
