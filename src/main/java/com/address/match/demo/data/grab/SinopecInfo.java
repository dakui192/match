package com.address.match.demo.data.grab;

import lombok.Data;

/**
 * @创建人 fbk
 * @创建时间 2020/6/8 3:10
 * @描述 中石化数据
 **/
@Data
public class SinopecInfo {
    /**
     * 经度
     */
    private Double lng=-180.0;
    /**
     * 维度
     */
    private Double lat=-90.0;
    /**
     * 地址信息
     */
    private String address;
    /**
     * 中石化公司
     */
    private String sinopec_name;
    /**
     * 电话号码
     */
    private String phone;

    @Override
    public String toString() {
        return "insert into match_sinopec_info(lng,lat,address,sinopec_name,phone) values(" +
                lng +
                ", " + lat +
                ",'" + address + '\'' +
                ",'" + sinopec_name + '\'' +
                ",'" + phone + '\'' +
                ");";
    }
}
