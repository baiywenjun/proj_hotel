package com.zxt.console.controller;

import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 17:53
 */
@Controller
@RequestMapping("/hotel/type")
public class HotelTypeController {

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelTypeList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return hotelRoomTypeService.queryHotelRoomTypeByPage(handle.getPage(),handle.getLimit());
    }

}
