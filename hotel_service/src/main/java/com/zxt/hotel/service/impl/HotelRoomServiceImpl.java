package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.mapper.HotelRoomMapper;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.pojo.HotelRoomFullVO;
import com.zxt.hotel.pojo.HotelRoomQuery;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.service.HotelRoomService;
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

    @Override
    /**
     * 分页查询房间信息
     * @param query
     * @param page
     * @param limit
     * @return
     */
    public Rt queryHotelRoomByPage(HotelRoomQuery query, Integer page, Integer limit){
        Wrapper<HotelRoom> wrapper = new EntityWrapper<>();
        if(query.getIsHotelId() != null){
            wrapper.eq("is_hotel_id",query.getIsHotelId());
        }
        if(query.getIsRoomTypeId() != null){
            wrapper.eq("is_room_type_id",query.getIsRoomTypeId());
        }
        if(query.getStayStatus() != null){
            wrapper.eq("stay_status",query.getStayStatus());
        }
        int count = this.selectCount(wrapper);
        Page<HotelRoom> hotelRoomPage = this.selectPage(new Page<>(page, limit), wrapper);
        List<HotelRoom> records = hotelRoomPage.getRecords();
        return Rt.ok(count,records);
    }
}
