package com.address.match.demo.data.dto;

import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 2:27
 * @描述 操作日志
 **/
@Data
public class MatchOperaLog implements Cloneable{
    /***
     * 用户ID
     */
    private String  user_id;
    /**
     * 状态 0中石化 1 4S店
     */
    private String state;
    /**
     * 查询的公司信息
     */
    private String query_name;
    /**
     * ip
     */
    private String ip;
    /**
     * 备注
     */
    private String  remark;

    /**
     * 浅复制
     * @return
     */
    public MatchOperaLog clone() {
        MatchOperaLog o = null;
        try {
            o = (MatchOperaLog) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
