package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.entity.HotelRoomType;
import com.zxt.hotel.pojo.HotelInfoQuery;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelInfoService;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Title: todoedit
 * Description: master
 * author: wenjun
 * date: 2018/6/21 16:34
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    /**
     * 酒店列表
     * @return
     */
    @RequestMapping("/hotel")
    public R hotelList(){
        // 后续根据权限显示登陆人可控的酒店
        HotelInfoQuery query = new HotelInfoQuery();
        List<HotelInfo> hotelInfoList = hotelInfoService.queryHotelInfo(query);
        return (hotelInfoList!=null && hotelInfoList.size()>0)?R.ok("success",hotelInfoList):R.error();
    }

    @RequestMapping("/roomtype")
    public R roomTypeList(Long hotelId){
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(hotelId);
        List<HotelRoomType> hotelRoomTypeList = hotelRoomTypeService.queryHotelRoomType(query);
        return (hotelRoomTypeList!=null && hotelRoomTypeList.size()>0)?R.ok("success",hotelRoomTypeList):R.error();
    }

}
