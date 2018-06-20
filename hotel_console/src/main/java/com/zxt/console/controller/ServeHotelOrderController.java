package com.zxt.console.controller;

import com.zxt.common.constant.shoConst;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;
import com.zxt.hotel.service.ServeHotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 酒店服务订单管理
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/7 14:13
 */
@Controller
@RequestMapping("/serve/hotel")
public class ServeHotelOrderController {

    @Autowired
    private ServeHotelOrderService serveHotelOrderService;

    /**
     * 查询服务订单
     * @param request
     * @param roomNo
     * @param isServeTypeId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lists")
    @ResponseBody
    public Rt serveHotelOrderList(HttpServletRequest request, String roomNo, Long isServeTypeId, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        ServeHotelOrderQuery query = new ServeHotelOrderQuery();
        query.setRoomNo(roomNo);
        query.setIsServeTypeId(isServeTypeId);
        return serveHotelOrderService.queryListByPage(query,handle.getPage(),handle.getLimit());
    }

    /**
     * 接受订单
     * @param serveHotelId id
     * @return r
     */
    @RequestMapping("/accept")
    @ResponseBody
    public R acceptOrder(Long serveHotelId){
        if(serveHotelId == null){
            return R.error(403,"[serveHotelId]不能为空");
        }
        ServeHotelOrder order = new ServeHotelOrder();
        order.setServeHotelId(serveHotelId);
        order.setStatus(shoConst.ACCEPT);
        boolean flag = serveHotelOrderService.updateById(order);
        return (flag)?R.ok():R.error();
    }

    /**
     * 完成
     * @param serveHotelId id
     * @return r
     */
    @RequestMapping("/over")
    @ResponseBody
    public R overOrder(Long serveHotelId){
        if(serveHotelId == null){
            return R.error(403,"[serveHotelId]不能为空");
        }
        ServeHotelOrder order = new ServeHotelOrder();
        order.setServeHotelId(serveHotelId);
        order.setStatus(shoConst.OVER);
        boolean flag = serveHotelOrderService.updateById(order);
        return (flag)?R.ok():R.error();
    }
}
