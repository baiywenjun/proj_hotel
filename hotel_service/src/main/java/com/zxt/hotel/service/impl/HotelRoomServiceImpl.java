package com.zxt.hotel.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxt.common.excel.HxlsCustomRead;
import com.zxt.common.exception.RRException;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelRoom;
import com.zxt.hotel.mapper.HotelRoomMapper;
import com.zxt.hotel.mapper.HotelRoomTypeMapper;
import com.zxt.hotel.pojo.HotelRoomExtVO;
import com.zxt.hotel.pojo.HotelRoomFullVO;
import com.zxt.hotel.pojo.HotelRoomQuery;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.service.HotelRoomService;
import com.zxt.hotel.service.RoomImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HotelRoomTypeMapper hotelRoomTypeMapper;

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

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


    @Override
    public Rt queryHotelRoomExtByPage(HotelRoomQuery query, Integer page, Integer limit){
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
        List<HotelRoomExtVO> hotelRoomExtVOList = hotelRoomMapper.queryHotelRoomExtByPage(new Page(page, limit), wrapper);
        return Rt.ok(count,hotelRoomExtVOList);
    }

    @Override
    public Long addRecord(HotelRoom hotelRoom){
        hotelRoom.setCreateTime(new Date());
        //hotelRoom.setStayStatus();
        this.insert(hotelRoom);
        Long roomId = hotelRoom.getRoomId();
        return roomId;
    }

    @Override
    public String processImportData(String filePath){
        Map<String, Object> param = new HashMap<>();

        long rowsSum = 0l;
        long successSum = 0l;
        long failureSum = 0l;
        try {
            HxlsCustomRead read = new HxlsCustomRead(filePath, 0, new RoomImport(),param);
            read.process();
            rowsSum = read.getOptRows_sum();
            successSum = read.getOptRows_success();
            failureSum = read.getOptRows_failure();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            log.error("===>"+"excel导入失败");
            throw new RRException("excel导入失败", 500);
        }

        StringBuilder buf = new StringBuilder();
        buf.append("总共数据[");
        buf.append(Long.toString(rowsSum - 1));
        buf.append("];");
        buf.append("导入成功[");
        buf.append(Long.toString(successSum));
        buf.append("];");
        if (failureSum > 0){
            buf.append("导入失败[");
            buf.append(failureSum);
            buf.append("]");
        }

        return buf.toString();
    }
}
