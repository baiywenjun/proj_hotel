package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelRoom;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/28 15:24
 */
public class HotelRoomFullVO {
    private HotelRoom hotelRoom;
    private HotelRoomTypeFullVO hotelTypeFull;

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public HotelRoomTypeFullVO getHotelTypeFull() {
        return hotelTypeFull;
    }

    public void setHotelTypeFull(HotelRoomTypeFullVO hotelTypeFull) {
        this.hotelTypeFull = hotelTypeFull;
    }

    @Override
    public String toString() {
        return "HotelRoomFullVO{" +
                "hotelRoom=" + hotelRoom +
                ", hotelTypeFull=" + hotelTypeFull +
                '}';
    }
}
