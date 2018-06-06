package com.zxt.hotel.service;

import com.zxt.hotel.entity.HotelRoom;
import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.pojo.HotelRoomFullVO;

/**
 * <p>
 * 酒店房间信息 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelRoomService extends IService<HotelRoom> {

    /**
     * 根据主键获取房间信息
     * @param roomId
     * @return
     */
    HotelRoomFullVO getById(Long roomId);
}
