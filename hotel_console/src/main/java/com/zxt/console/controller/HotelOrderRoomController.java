package com.zxt.console.controller;

import com.zxt.common.result.R;
import com.zxt.hotel.entity.HotelOrderRoom;
import com.zxt.hotel.service.HotelOrderRoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 房间历史订单
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/23 8:58
 */
@Controller
@RequestMapping("/hotel/order/room")
public class HotelOrderRoomController {
    private static Logger log = LoggerFactory.getLogger(HotelOrderRoomController.class);

    @Autowired
    private HotelOrderRoomService hotelOrderRoomService;


    @PostMapping("/add")
    @ResponseBody
    public R addHotelOrderRoom(@RequestBody HotelOrderRoom hotelOrderRoom){
        // todo
        return null;
    }


    /**
     * 线上分房
     * @param hotelOrderId
     * @param roomIds
     * @return
     */
    @PostMapping("/allot-room-online")
    @ResponseBody
    public R allotRoomOnline(Long hotelOrderId, String roomIds){
        if(hotelOrderId == null){
            return R.error(403,"酒店主键为空");
        }
        if(StringUtils.isEmpty(roomIds)){
            return R.error(403,"房间不能为空");
        }
        String[] split = roomIds.split(",");
        Boolean flag = hotelOrderRoomService.addRecordByAllotRoom(hotelOrderId, split);
        return (flag)?R.ok():R.error();
    }


}
