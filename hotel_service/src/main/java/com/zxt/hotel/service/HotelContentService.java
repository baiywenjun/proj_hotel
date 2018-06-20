package com.zxt.hotel.service;

import com.zxt.hotel.entity.HotelContent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店描述相关 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-05-21
 */
public interface HotelContentService extends IService<HotelContent> {

    /**
     * 批量新增酒店描述记录
     * @param hotelContentList hotelContentList
     * @return flag
     */
    List<Long> batchInsertRecord(List<HotelContent> hotelContentList);
}
