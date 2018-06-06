package com.zxt.api.controller;

import com.zxt.common.result.R;
import com.zxt.hotel.pojo.HotelRoomFullVO;
import com.zxt.hotel.service.HotelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/28 14:53
 */
@RestController
@RequestMapping("/hotel/room")
@Api(tags = "房间信息")
public class HotelRoomController {

    private static Logger log = LoggerFactory.getLogger(HotelRoomController.class);

    @Autowired
    private HotelRoomService hotelRoomService;

    @RequestMapping("/get")
    @ApiOperation(httpMethod = "GET", value="查询房间信息")
    public R getRoomInfoByScanQr(Long roomId){
        if(roomId == null){
            return R.error(403,"房间主键不能为空");
        }
        HotelRoomFullVO hotelRoomFullVO = hotelRoomService.getById(roomId);
        return R.ok("success",hotelRoomFullVO);
    }

}
