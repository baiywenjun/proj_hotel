package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelInfo;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/25 17:39
 */
public class HotelInfoExt extends HotelInfo {
    // 距离
    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HotelInfoExt{" +
                "distance='" + distance + '\'' +
                '}';
    }
}
