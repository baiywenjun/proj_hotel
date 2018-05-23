package com.zxt.api.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.HotelOrder;
import com.zxt.hotel.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: 酒店订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/21 9:55
 */
@RestController
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

    /**
     * 新增一条订单
     * 1.未付款
     * @param hotelOrder
     * @return
     */
    @PostMapping("/add")
    public R addHotelOrder(@RequestBody HotelOrder hotelOrder) {
        // todo 欠缺校验，如金额校验
        Boolean flag = hotelOrderService.addHotelOrder(hotelOrder);
        return (flag) ? R.ok() : R.error();
    }

    /**
     * 修改订单
     * 1.已付款，添加订单支付方式，支付状态，支付流水
     * 1.已分房，添加房间编号或id
     * @param hotelOrder
     * @return
     */
    @PostMapping("/update")
    public R updateHotelOrder(@RequestBody HotelOrder hotelOrder){
        Boolean flag = hotelOrderService.updateHotelOrder(hotelOrder);
        return (flag)?R.ok():R.error();
    }


}
