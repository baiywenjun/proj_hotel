package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: 酒店订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 9:55
 */
@Controller
@RequestMapping("/hotel/order")
public class HotelOrderController {

    @Autowired
    private HotelOrderService hotelOrderService;



}
