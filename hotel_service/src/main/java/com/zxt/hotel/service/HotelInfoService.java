package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.HotelInfoQuery;

/**
 * <p>
 * 酒店信息 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface HotelInfoService extends IService<HotelInfo> {

    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    Rt queryHotelInfoByPage(HotelInfoQuery query, Integer page, Integer limit);

    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    Rt queryHotelInfoFullByPage(HotelInfoQuery query, Integer page, Integer limit);

    Boolean addHotelInfo(HotelInfo hotel);

    Boolean updateHotelInfo(HotelInfo hotel);
}
