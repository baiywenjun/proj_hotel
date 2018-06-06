package com.zxt.hotel.service.impl;

import com.zxt.common.result.R;
import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.mapper.HotelRoomMapper;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.pojo.HotelRoomFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒店房间信息 服务实现类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
@Service
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomMapper, HotelRoom> implements HotelRoomService {

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    @Override
    /**
     * 根据主键获取房间信息
     * @param roomId
     * @return
     */
    public HotelRoomFullVO getById(Long roomId){
        HotelRoom hotelRoom = this.selectById(roomId);
        Long isRoomTypeId = hotelRoom.getIsRoomTypeId();
        HotelRoomTypeFullVO hotelRoomTypeFullVO = hotelRoomTypeMapper.queryById(isRoomTypeId);
        HotelRoomFullVO hotelRoomFullVO = new HotelRoomFullVO();
        hotelRoomFullVO.setHotelRoom(hotelRoom);
        hotelRoomFullVO.setHotelTypeFull(hotelRoomTypeFullVO);
        return hotelRoomFullVO;
    }
}
