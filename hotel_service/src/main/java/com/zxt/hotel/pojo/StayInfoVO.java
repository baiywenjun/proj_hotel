package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelInfo;

/**
 * Title: 入住信息
 * Description: 退房或续租时的入住信息展示
 * author: wenjun
 * date: 2018/5/23 17:51
 */
public class StayInfoVO {

    private HotelInfo hotelInfo;
    private String roomNo;
    private Long hotelOrderRoomId;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Long getHotelOrderRoomId() {
        return hotelOrderRoomId;
    }

    public void setHotelOrderRoomId(Long hotelOrderRoomId) {
        this.hotelOrderRoomId = hotelOrderRoomId;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }
}
