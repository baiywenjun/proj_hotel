package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;

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
    Rt queryHotelInfoByPage(Integer page, Integer limit);

    Boolean addHotelInfo(HotelInfo hotel);
}
