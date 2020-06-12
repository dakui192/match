package com.address.match.demo.data.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 11:30
 * @描述 前台查询对接信息
 **/
@Data
@ApiModel(description = "信息查询类")
public class MatchQueryDataVO {
    /**
     * 地址信息
     */
    @ApiModelProperty(value = "地址",example="重庆安福汽车营销有限公司万州分公司",required=true)
    private String address;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",example="f23456",required=true)
    private String user_id;
}
