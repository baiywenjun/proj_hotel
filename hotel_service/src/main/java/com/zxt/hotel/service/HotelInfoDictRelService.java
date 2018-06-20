package com.zxt.hotel.service;

import com.zxt.hotel.entity.HotelInfoDictRel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 酒店与字典关系表 服务类
 * </p>
 *
 * @author wenjun
 * @since 2018-06-12
 */
public interface HotelInfoDictRelService extends IService<HotelInfoDictRel> {

    /**
     * 批量新酒店与设施的关联记录
     * @param hotelId hotelId
     * @param dictIdList dictIdList
     * @return flag
     */
    Boolean batchInsertRecord(Long hotelId, List<Long> dictIdList);

    /**
     * 重新对应酒店与设施的关联记录
     * @param hotelId
     * @param dictIdList
     * @return
     */
    Boolean rebuildRecord(Long hotelId, List<Long> dictIdList);
}
