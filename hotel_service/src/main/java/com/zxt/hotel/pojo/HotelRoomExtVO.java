package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.entity.HotelRoomType;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/20 17:19
 */
public class HotelRoomExtVO extends HotelRoom {
    private HotelRoomType hotelRoomType;

    public HotelRoomType getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(HotelRoomType hotelRoomType) {
        this.hotelRoomType = hotelRoomType;
    }

    @Override
    public String toString() {
        return "HotelRoomExtVO{" +
                "hotelRoomType=" + hotelRoomType +
                '}';
    }
}
