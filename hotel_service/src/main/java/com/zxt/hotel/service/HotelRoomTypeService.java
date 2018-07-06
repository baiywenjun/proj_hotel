package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelRoomType;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 酒店sku 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface HotelRoomTypeService extends IService<HotelRoomType> {

    /**
     * 给profile使用
     * @param query
     * @return
     */
    List<HotelRoomType> queryHotelRoomType(HotelRoomTypeQuery query);

    Rt queryHotelRoomTypeByPage(HotelRoomTypeQuery query, Integer page, Integer limit);

    Rt queryHotelRoomTypeFullByPage(HotelRoomTypeQuery query, Integer page, Integer limit);

    HotelRoomTypeFullVO findOneById(Long roomTypeId);

    /**
     * 新增房型信息
     * @param hotelRoomType
     * @param dictId
     * @return
     */
    Boolean addRecord(HotelRoomType hotelRoomType, Long dictId);

    Rt queryHotelRoomTypeNHotelByPage(HotelRoomTypeQuery query, Integer page, Integer limit);

    R addHotelRoomType(Map map);
}
