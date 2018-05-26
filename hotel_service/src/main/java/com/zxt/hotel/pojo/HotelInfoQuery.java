package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.service.HotelInfoService;

/**
 * Title: 酒店信息查询条件
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/22 15:33
 */
public class HotelInfoQuery extends HotelInfo {

    // 经度
    private String lng;
    // 纬度
    private String lat;

    @Override
    public String getLng() {
        return lng;
    }

    @Override
    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String getLat() {
        return lat;
    }

    @Override
    public void setLat(String lat) {
        this.lat = lat;
    }
}
