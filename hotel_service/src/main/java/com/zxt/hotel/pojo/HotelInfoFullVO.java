package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.entity.HotelDict;
import com.zxt.hotel.entity.HotelInfo;

import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/12 11:06
 */
public class HotelInfoFullVO extends HotelInfo {
    // 距离
    private String distance;
    private List<HotelDict> hotelDictList;
    private List<HotelContent> hotelContentList;

    public List<HotelDict> getHotelDictList() {
        return hotelDictList;
    }

    public void setHotelDictList(List<HotelDict> hotelDictList) {
        this.hotelDictList = hotelDictList;
    }

    public List<HotelContent> getHotelContentList() {
        return hotelContentList;
    }

    public void setHotelContentList(List<HotelContent> hotelContentList) {
        this.hotelContentList = hotelContentList;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HotelInfoFullVO{" +
                "distance='" + distance + '\'' +
                ", hotelDictList=" + hotelDictList +
                ", hotelContentList=" + hotelContentList +
                '}';
    }
}
