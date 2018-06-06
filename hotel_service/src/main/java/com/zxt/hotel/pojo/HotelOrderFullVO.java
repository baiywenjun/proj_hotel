package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.entity.HotelRoomType;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/29 14:35
 */
public class HotelOrderFullVO extends HotelOrder {

    private HotelInfo hotelInfo;
    private HotelRoomType hotelRoomType;

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public HotelRoomType getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(HotelRoomType hotelRoomType) {
        this.hotelRoomType = hotelRoomType;
    }

    @Override
    public String toString() {
        return "HotelOrderFullVO{" +
                "hotelInfo=" + hotelInfo +
                ", hotelRoomType=" + hotelRoomType +
                '}';
    }
}
