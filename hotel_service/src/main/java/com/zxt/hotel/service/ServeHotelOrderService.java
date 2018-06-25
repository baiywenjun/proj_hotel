package com.zxt.hotel.service;

import com.zxt.common.customs.CallBack;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.ServeHotelOrder;
import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.pojo.ServeHotelOrderFullVO;
import com.zxt.hotel.pojo.ServeHotelOrderQuery;

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

    /**
     * 后台查询服务订单
     * @param query
     * @param page
     * @param limit
     * @return
     */
    Rt queryListByPage(ServeHotelOrderQuery query, Integer page, Integer limit);

    /**
     * 后台查询服务订单(根据serve_hotel_id查询)
     * @param query
     * @param page
     * @param limit
     * @return
     */
    Rt queryListByPage2(ServeHotelOrderQuery query, Integer page, Integer limit);

    /**
     * 后台查询服务订单详细(根据serve_hotel_id查询,增加回调)
     * @param query
     * @param page
     * @param limit
     * @param callback
     * @return
     */
    Rt queryListByPageNCallback(ServeHotelOrderQuery query, Integer page, Integer limit,CallBack<ServeHotelOrderFullVO> callback);
}
