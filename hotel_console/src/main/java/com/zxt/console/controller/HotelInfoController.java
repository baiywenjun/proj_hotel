package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.service.HotelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 16:34
 */
@Controller
@RequestMapping("/hotel/info")
public class HotelInfoController {

    @Autowired
    private HotelInfoService hotelInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelIntoList(String hotelName, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return hotelInfoService.queryHotelInfoByPage(handle.getPage(), handle.getLimit());
    }


    @PostMapping("/add")
    @ResponseBody
    public R addHotelInfo(@RequestBody HotelInfo hotel){
        // todo 参数校验后续
        Boolean flag = hotelInfoService.addHotelInfo(hotel);
        return (flag)?R.ok():R.error();
    }

}
