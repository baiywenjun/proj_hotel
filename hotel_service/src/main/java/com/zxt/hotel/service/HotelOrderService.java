package com.zxt.hotel.service;

import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelOrder;
import com.baomidou.mybatisplus.service.IService;
import com.zxt.hotel.pojo.HotelOrderQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelOrderService extends IService<HotelOrder> {

    Rt queryHotelOrderList(HotelOrderQuery query, Integer page, Integer limit);

    Map<String,Object> addHotelOrder(HotelOrder hotelOrder);

    Boolean updateHotelOrder(HotelOrder hotelOrder);

    /**
     * 支付成功后，更新订单
     * @param paymentType
     * @param orderNo
     * @param payNo
     * @return
     */
    void updateOrderByPaid(String paymentType,String orderNo,  String payNo);
}
