package com.zxt.hotel.pojo;

import com.zxt.hotel.entity.HotelContent;
import com.zxt.hotel.entity.HotelDict;
import com.zxt.hotel.entity.HotelInfo;

import java.util.List;

/**
 * Title: 酒店详情
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/25 10:20
 */
public class HotelInfoFullDistanceVO {

    private HotelInfoExt hotelInfoExt;
    // 规格
    private List<HotelDict> hotelDictList;
    // 酒店详情
    private List<HotelContent> hotelContentList;

    public HotelInfoExt getHotelInfoExt() {
        return hotelInfoExt;
    }

    public void setHotelInfoExt(HotelInfoExt hotelInfoExt) {
        this.hotelInfoExt = hotelInfoExt;
    }

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

}
