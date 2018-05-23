package com.zxt.api.controller;

import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 17:53
 */
@RestController
@RequestMapping("/hotel/type")
public class HotelTypeController {

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelTypeList(Long hotelInfoId, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(hotelInfoId);
        return hotelRoomTypeService.queryHotelRoomTypeByPage(query,handle.getPage(),handle.getLimit());
    }

}
