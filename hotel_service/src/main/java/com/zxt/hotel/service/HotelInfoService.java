package com.zxt.hotel.service;

import com.baomidou.mybatisplus.service.IService;
import com.zxt.common.result.Rt;
import com.zxt.hotel.entity.HotelInfo;
import com.zxt.hotel.pojo.HotelInfoFullVO;
import com.zxt.hotel.pojo.HotelInfoQuery;

import java.util.List;

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
     * 给profile使用
     * @param query
     * @return
     */
    List<HotelInfo> queryHotelInfo(HotelInfoQuery query);

    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    Rt queryHotelInfoByPage(HotelInfoQuery query, Integer page, Integer limit);


    /**
     * 根据主键查找酒店详细信息
     * @param hotelId
     * @return
     */
    HotelInfoFullVO queryHotelInfoFUllVOById(Long hotelId);

    /**
     * 酒店列表带距离
     * @param query
     * @param page
     * @param limit
     * @return
     */
    Rt queryHotelInfoFullDistanceByPage(HotelInfoQuery query, Integer page, Integer limit);

    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    Rt queryHotelInfoFullByPage(HotelInfoQuery query, Integer page, Integer limit);

    /**
     * 酒店列表带距离,过渡
     * @param query
     * @param page
     * @param limit
     * @return
     */
    Rt queryHotelInfoFullDistanceByPageOver(HotelInfoQuery query, Integer page, Integer limit);

    @Deprecated
    /**
     * 查询酒店信息
     * @param page page
     * @param limit limit
     * @return rt
     */
    Rt queryHotelInfoFullByPageDiscard(HotelInfoQuery query, Integer page, Integer limit);

    Long addHotelInfo(HotelInfo hotel);

    Boolean updateHotelInfo(HotelInfo hotel);
}
