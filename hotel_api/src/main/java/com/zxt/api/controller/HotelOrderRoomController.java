package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.result.R;
import com.zxt.hotel.entity.HotelOrderRoom;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.pojo.StayInfoVO;
import com.zxt.hotel.service.HotelOrderRoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 房间历史订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/23 8:58
 */
@RestController
@RequestMapping("/hotel/order/room")
public class HotelOrderRoomController {
    private static Logger log = LoggerFactory.getLogger(HotelOrderRoomController.class);

    @Autowired
    private HotelOrderRoomService hotelOrderRoomService;

    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public R addHotelOrderRoom(@RequestBody HotelOrderRoom hotelOrderRoom){
        // to do something 用户通过其他方式
        return null;
    }

    /**
     * 查看入住信息
     * @param request
     * @return
     */
    @RequestMapping("/stay-info")
    public R viewStayInfo(HttpServletRequest request){
        SysUser sysUser = authService.getUserInfoByReq(request);
        StayInfoVO stayInfo = hotelOrderRoomService.getStayInfo(sysUser);
        if(stayInfo == null){
            return R.error(204,"没有入住信息");
        }
        return R.ok("success",stayInfo);
    }

    /**
     * 用户通过扫码，可以访问该路径，来添加房间订单信息
     * @param roomId
     * @param request
     * @return
     */
    @RequestMapping("/scan-add")
    public R addRecordByUserScan(String roomId, HttpServletRequest request){
        if(! StringUtils.isNumeric(roomId)){
            return R.error(403,"二维码缺少主键信息");
        }
        SysUser sysUser = authService.getUserInfoByReq(request);
        Boolean flag = hotelOrderRoomService.addRecordByUserScan(Long.parseLong(roomId), sysUser);
        return (flag)?R.ok():R.error();
    }


    /**
     * 退房操作
     * @param roomId
     * @param request
     * @return
     */
    public R quitRoom(String roomId, HttpServletRequest request){


        return null;
    }
}
