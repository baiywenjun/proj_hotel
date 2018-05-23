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

    /**
     * 列表
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    public Rt hotelOrderList(Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return hotelOrderService.queryHotelOrderList(handle.getPage(), handle.getLimit());
    }


    @PostMapping("/add")
    public R addHotelOrder(@RequestBody HotelOrder hotelOrder){
        // todo 欠缺校验，如金额校验
        Boolean flag = hotelOrderService.addHotelOrder(hotelOrder);
        return (flag)?R.ok():R.error();
    }

}
