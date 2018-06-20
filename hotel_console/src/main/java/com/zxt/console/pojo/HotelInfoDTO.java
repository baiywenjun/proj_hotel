package com.zxt.console.pojo;

import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.entity.HotelInfo;

import java.util.List;

/**
 * Title: 酒店提交实体
 * Description: master
 * author: wenjun
 * date: 2018/6/13 16:08
 */
public class HotelInfoDTO {
    // HotelDictIds
    private HotelInfo hotelInfo;
    private List<Long> businessId;
    private List<HotelContent> hotelContentList;

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public List<Long> getBusinessId() {
        return businessId;
    }

    public void setBusinessId(List<Long> businessId) {
        this.businessId = businessId;
    }

    public List<HotelContent> getHotelContentList() {
        return hotelContentList;
    }

    public void setHotelContentList(List<HotelContent> hotelContentList) {
        this.hotelContentList = hotelContentList;
    }

    @Override
    public String toString() {
        return "HotelInfoDTO{" +
                "hotelInfo=" + hotelInfo +
                ", businessId=" + businessId +
                ", hotelContentList=" + hotelContentList +
                '}';
    }
}
