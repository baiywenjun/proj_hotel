package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelRoomType;
import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;

/**
 * <p>
 * 酒店sku 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface HotelRoomTypeService extends IService<HotelRoomType> {

    Rt queryHotelRoomTypeByPage(HotelRoomTypeQuery query, Integer page, Integer limit);
}
