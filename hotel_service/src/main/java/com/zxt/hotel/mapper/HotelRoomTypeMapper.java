package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.HotelRoomType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeNHotelVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 酒店sku Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface HotelRoomTypeMapper extends BaseMapper<HotelRoomType> {

    List<HotelRoomTypeFullVO> queryHotelRoomTypeFullByPage(Page page, @Param("query") HotelRoomTypeQuery query);

    HotelRoomTypeFullVO queryById(Long roomTypeId);

    List<HotelRoomTypeNHotelVO> queryHotelRoomTypeNHotelByPage(Page page, @Param("query") HotelRoomTypeQuery query);

}
