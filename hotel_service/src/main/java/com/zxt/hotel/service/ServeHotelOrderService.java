package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 酒店服务 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface ServeHotelOrderService extends IService<ServeHotelOrder> {

    /**
     * 查询用户自己的订单
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    Rt queryListByUser(Long userId, Integer page, Integer limit);

    ServeHotelOrder findOneById(Long serveHotelId);

    Boolean cancelOrder(Long serveHotelId);
}
