package com.zxt.console.controller;

import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.HotelRoomQuery;
import com.zxt.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: 房间
 * Description: todoedit
 * author: wenjun
 * date: 2018/6/6 16:48
 */
@Controller
@RequestMapping("/hotel/room")
public class HotelRoomController {

    @Autowired
    private HotelRoomService hotelRoomService;

    /**
     * 房间查询
     * @param hotelId
     * @param roomTypeId
     * @param stayStatus
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Rt queryHotelRoomList(Long hotelId, Long roomTypeId, String stayStatus, Integer page, Integer limit){
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomQuery query = new HotelRoomQuery();
        query.setIsHotelId(hotelId);
        query.setIsRoomTypeId(roomTypeId);
        query.setStayStatus(stayStatus);
        return hotelRoomService.queryHotelRoomByPage(query, handle.getPage(), handle.getLimit());
    }

}
