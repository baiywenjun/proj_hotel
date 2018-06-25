package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.HotelRoom;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zxt.hotel.pojo.HotelRoomExtVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 酒店房间信息 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelRoomMapper extends BaseMapper<HotelRoom> {

    List<HotelRoomExtVO> queryHotelRoomExtByPage(Page page, @Param("ew")Wrapper wrapper);
}
