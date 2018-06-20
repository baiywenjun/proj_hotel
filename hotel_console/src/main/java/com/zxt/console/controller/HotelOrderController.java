package com.zxt.console.controller;

import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.HotelOrderQuery;
import com.zxt.hotel.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/page")
    public String hotelOrderPage(){
        return "components/hotel-order";
    }

    @RequestMapping("/lists")
    @ResponseBody
    public Rt hotelOrderList(HttpServletRequest request,String userRealName, String userPhone, String paymentStatus,
                             Integer page, Integer limit){
        // todo 后续基于权限，查询登陆人所属权限的订单
        HotelOrderQuery query = new HotelOrderQuery();
        query.setUserRealName(userRealName);
        query.setUserPhone(userPhone);
        query.setPaymentStatus(paymentStatus);
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return hotelOrderService.queryHotelOrderList(query, handle.getPage(), handle.getLimit());
    }


}
