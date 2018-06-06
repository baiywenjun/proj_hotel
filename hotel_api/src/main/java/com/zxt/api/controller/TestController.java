package com.zxt.api.controller;

import com.zxt.common.exception.RRException;
import com.zxt.hotel.service.HotelOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/17 11:43
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HotelOrderService hotelOrderService;

    @RequestMapping("/")
    public String test(){
        return "hello world";
    }

    @RequestMapping("/hello")
    public String test2(String id){
        if(StringUtils.isEmpty(id)){
            throw new RRException("hello");
        }
        return "ok";
    }

    @RequestMapping("/back")
    public String orderBack(){
        String paymentType = "wechat";
        String orderNo = "H15278369504361852";
        String payNo = "4200000152201806019779184073";
        hotelOrderService.updateOrderByPaid(paymentType,orderNo,payNo);
        return "over";
    }
}
