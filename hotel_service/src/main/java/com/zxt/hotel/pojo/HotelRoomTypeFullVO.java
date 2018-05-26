package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelDict;
import com.zxt.hotel.entity.HotelRoomType;

import java.util.List;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/26 10:00
 */
public class HotelRoomTypeFullVO extends HotelRoomType {
    private List<HotelDict> hotelDictList;

    public List<HotelDict> getHotelDictList() {
        return hotelDictList;
    }

    public void setHotelDictList(List<HotelDict> hotelDictList) {
        this.hotelDictList = hotelDictList;
    }

    @Override
    public String toString() {
        return "HotelRoomTypeFullVO{" +
                "hotelDictList=" + hotelDictList +
                '}';
    }
}
