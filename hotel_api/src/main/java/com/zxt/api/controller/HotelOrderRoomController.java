package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.result.R;
import com.zxt.hotel.entity.HotelOrderRoom;
import com.zxt.hotel.entity.SysUser;
import com.zxt.hotel.pojo.StayInfoVO;
import com.zxt.hotel.service.HotelOrderRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 房间历史订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/23 8:58
 */
@RestController
@RequestMapping("/hotel/order/room")
@Api(tags = "房间订单")
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
    @ApiOperation(httpMethod = "GET", value="查看入住信息")
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
    @ApiOperation(httpMethod = "GET", value="扫码添加房间订单信息")
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
     * @param orderRoomId
     * @return
     */
    @RequestMapping("/quit")
    @ApiOperation(httpMethod = "GET", value="退房操作")
    public R quitRoom(String roomId, String orderRoomId){
        if(StringUtils.isEmpty(roomId)){
            return R.error(403,"房间主键不能为空");
        }
        if(StringUtils.isEmpty(orderRoomId)){
            return R.error(403,"房间订单主键不能为空");
        }
        Boolean flag = hotelOrderRoomService.quitRoom(Long.parseLong(roomId), Long.parseLong(orderRoomId));
        return (flag)?R.ok():R.error();
    }

    /**
     * 续住
     * @param hotelOrderId
     * @return
     */
    @PostMapping("/continue")
    @ApiOperation(httpMethod = "POST", value = "续住，生成房间订单")
    public R continueStay(Long hotelOrderId){
        if(hotelOrderId == null){
            return R.error(403,"订单主键不能为空");
        }
        Boolean flag = hotelOrderRoomService.continueOrder(hotelOrderId);
        return (flag)?R.ok():R.error();
    }
}
