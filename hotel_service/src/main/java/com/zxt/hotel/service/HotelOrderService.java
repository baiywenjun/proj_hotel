package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelOrderService extends IService<HotelOrder> {

    Rt queryHotelOrderList(Integer page,Integer limit);

    Boolean addHotelOrder(HotelOrder hotelOrder);

    Boolean updateHotelOrder(HotelOrder hotelOrder);
}
