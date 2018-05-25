package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.constant.shoConst;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.DateUtil;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.service.ServeHotelOrderService;
import com.zxt.hotel.service.ServeTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Title: 酒店服务
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/24 14:04
 */
@RestController
@RequestMapping("/serve/hotel")
@Api(tags = "酒店服务")
public class ServeHotelOrderController {

    @Autowired
    private ServeTypeService serveTypeService;

    @Autowired
    private ServeHotelOrderService serveHotelOrderService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/serve-type-list")
    @ApiOperation(httpMethod = "GET", value="酒店服务列表")
    public Rt showServeContent(){
        return serveTypeService.serveTypeList();
    }

    @PostMapping("/add")
    @ApiOperation(httpMethod = "POST", value="新增订单")
    public R addRecord(Long hotelOrderRoomId,Long hotelOrderId, Long serveTypeId, String remark, String timeOut,
                       HttpServletRequest request){
        if(hotelOrderRoomId == null){
            return R.error(403,"hotelOrderRoomId不能为空");
        }
        if(serveTypeId == null){
            return R.error(403,"serveTypeId不能为空");
        }
        SysUser sysUser = authService.getUserInfoByReq(request);
        ServeHotelOrder serveHotelOrder = new ServeHotelOrder();
        serveHotelOrder.setIsOrderRoomId(hotelOrderRoomId);
        serveHotelOrder.setIsOrderId(hotelOrderId);
        serveHotelOrder.setIsServeTypeId(serveTypeId);
        serveHotelOrder.setPhone(sysUser.getPhone());
        serveHotelOrder.setRemark(remark);
        Date timeOutDate = DateUtil.parseDateTime(timeOut);
        serveHotelOrder.setTimeOut(timeOutDate);
        serveHotelOrder.setStatus(shoConst.APPLY);
        serveHotelOrder.setCreateTime(new Date());
        boolean flag = serveHotelOrderService.insert(serveHotelOrder);
        return (flag)?R.ok():R.error();
    }

}
