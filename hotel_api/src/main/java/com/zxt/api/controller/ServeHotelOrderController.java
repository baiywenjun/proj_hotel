package com.zxt.api.controller;

import com.zxt.api.sersvice.AuthService;
import com.zxt.common.constant.shoConst;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
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
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/my-list")
    @ApiOperation(httpMethod = "GET", value="用户服务订单列表")
    public Rt serveHotelOrderList(HttpServletRequest request, Integer page, Integer limit){
        SysUser sysUser = authService.getUserInfoByReq(request);
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        return serveHotelOrderService.queryListByUser(sysUser.getUserId(),handle.getPage(),handle.getLimit());
    }

    @RequestMapping("/find-one")
    @ApiOperation(httpMethod = "GET", value="根据主键查询服务订单")
    public R findOneById(Long serveHotelId){
        if(serveHotelId == null){
            return R.error(403,"主键不能为空");
        }
        ServeHotelOrder serveHotelOrder = serveHotelOrderService.findOneById(serveHotelId);
        return (serveHotelOrder != null)?R.ok("success",serveHotelOrder):R.error();
    }

    @PostMapping("/add")
    @ApiOperation(httpMethod = "POST", value="新增订单")
    public R addRecord(Long hotelOrderRoomId,Long hotelOrderId, Long serveTypeId, String remark, Long timeOut,
                       String hotelName, Long isRoomId, String roomNo, String serveName, HttpServletRequest request){
        if(hotelOrderRoomId == null){
            return R.error(403,"hotelOrderRoomId不能为空");
        }
        if(serveTypeId == null){
            return R.error(403,"serveTypeId不能为空");
        }
        if(isRoomId == null){
            return R.error(403,"isRoomId不能为空");
        }
        SysUser sysUser = authService.getUserInfoByReq(request);
        ServeHotelOrder serveHotelOrder = new ServeHotelOrder();
        serveHotelOrder.setIsOrderRoomId(hotelOrderRoomId);
        serveHotelOrder.setIsOrderId(hotelOrderId);
        serveHotelOrder.setIsServeTypeId(serveTypeId);
        serveHotelOrder.setPhone(sysUser.getPhone());
        serveHotelOrder.setIsUserId(sysUser.getUserId());
        serveHotelOrder.setRemark(remark);
        serveHotelOrder.setHotelName(hotelName);
        serveHotelOrder.setIsRoomId(isRoomId);
        serveHotelOrder.setRoomNo(roomNo);
        serveHotelOrder.setServeName(serveName);
        //serveHotelOrder.setPhone(phone);
        //Date timeOutDate = DateUtil.parseDateTime(timeOut);
        Date timeOutDate = new Date(timeOut);
        serveHotelOrder.setTimeOut(timeOutDate);
        serveHotelOrder.setStatus(shoConst.APPLY);
        serveHotelOrder.setCreateTime(new Date());
        boolean flag = serveHotelOrderService.insert(serveHotelOrder);
        Long serveHotelId = serveHotelOrder.getServeHotelId();
        Map<String,Object> map = new HashMap<>(1);
        map.put("serveHotelId",serveHotelId);
        return (flag)?R.ok(map):R.error();
    }


    @RequestMapping("/cancel")
    @ApiOperation(httpMethod = "GET", value="取消订单")
    public R cancelOrder(Long serveHotelId){
        if(serveHotelId == null){
            return R.error(403,"主键不能为空");
        }
        Boolean flag = serveHotelOrderService.cancelOrder(serveHotelId);
        return (flag)?R.ok():R.error();
    }

}
